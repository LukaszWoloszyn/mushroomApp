package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class MainActivity : BaseActivity() {

    private lateinit var tflite: Interpreter
    private val labels = listOf("Gąska Murrilla", "Muchomor sromotnikowy", "Opieńka miodowa")
    private val PICK_IMAGE_REQUEST = 1

    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.content_frame), true)

        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)


        // Ładowanie modelu
        try {
            tflite = Interpreter(loadModelFile("mushroom_classifier.tflite"))
            Toast.makeText(this, "Model załadowany poprawnie", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("TFLite", "Błąd podczas ładowania modelu", e)
            Toast.makeText(this, "Nie udało się załadować modelu", Toast.LENGTH_SHORT).show()
            return
        }

        // Inicjalizacja widoków
        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)

        // Konfiguracja Bottom Navigation
        setupBottomNavigation()

        // Konfiguracja FAB
        val cameraButton = findViewById<FloatingActionButton>(R.id.cameraButton)
        cameraButton.setOnClickListener {
            // Bezpośrednie przejście do CameraActivity
            val intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        updateNavMenu()

    }

    private fun setupBottomNavigation() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Dostosowanie wyglądu, aby środkowy element był niewidoczny (miejsce na FAB)
        bottomNavigation.menu.getItem(1).isEnabled = false

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.gallery -> {
                    // Wybierz zdjęcie z galerii
                    openGallery()
                    true
                }
                R.id.classify -> {
                    // Pobierz bitmapę z ImageView
                    val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
                    if (bitmap != null) {
                        val inputBuffer = preprocessImage(bitmap)
                        val prediction = classifyImage(inputBuffer)
                        resultTextView.text = prediction
                    } else {
                        Toast.makeText(this, "Wybierz obraz przed klasyfikacją!", Toast.LENGTH_SHORT).show()
                    }

                    true
                }

                else -> false
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        startActivityForResult(Intent.createChooser(intent, "Wybierz obraz"), PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            if (imageUri != null) {
                try {
                    val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    imageView.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Log.e("ImageSelection", "Błąd podczas wczytywania obrazu", e)
                    Toast.makeText(this, "Nie udało się wczytać obrazu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Przekazywanie przetworzonego obrazu do mogelu
    private fun classifyImage(inputBuffer: ByteBuffer): String {

        // Tworzenie buforu wyjściowego
        val outputBuffer = ByteBuffer.allocateDirect(4 * labels.size) // Wyjście (float32)
        outputBuffer.order(ByteOrder.nativeOrder())

        return try {
            // Wysłanie obrazu do modelu i pobranie wyników
            tflite.run(inputBuffer, outputBuffer)

            // Konwersja wyników na tablicę liczb
            val probabilities = FloatArray(labels.size)
            outputBuffer.rewind()
            outputBuffer.asFloatBuffer().get(probabilities)

            probabilities.forEachIndexed { index, probability ->
                Log.d("TFLite", "Klasa: ${labels[index]}, Prawdopodobieństwo: ${probability * 100}%")
            }

            // Znajdowanie klasy z najwyższym prawdopodobieństwem
            val predictedIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
            val maxProbability = probabilities.getOrElse(predictedIndex) { 0f }

            // Sprawdzenie, czy maksymalne prawdopodobieństwo przekracza próg
            val threshold = 0.5f // Próg 60%
            if (maxProbability < threshold) {
                "Nie rozpoznano żadnego grzyba. Wszystkie klasy mają prawdopodobieństwo poniżej ${threshold * 100}%."
            } else {
                val result = probabilities.mapIndexed { index, prob ->
                    "${labels[index]}: ${(prob * 100).toInt()}%"
                }.joinToString("\n")

                "Rozpoznano: ${labels[predictedIndex]}\n\nSzczegóły:\n$result"
            }
        } catch (e: Exception) {
            Log.e("TFLite", "Błąd podczas klasyfikacji", e)
            "Błąd klasyfikacji"
        }
    }

    // Otwarcie modelu i wczytanie go do ByteBuffer (mapowanie do pamięci, co jest szybsze)
    private fun loadModelFile(fileName: String): ByteBuffer {
        val assetFileDescriptor = assets.openFd(fileName)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    // Przygotowanie obrazu do klasyfikacji
    private fun preprocessImage(bitmap: Bitmap): ByteBuffer {

        // Pobranie informacji o wejściu modelu
        val inputTensor = tflite.getInputTensor(0)
        val inputShape = inputTensor.shape()
        val inputDataType = inputTensor.dataType()

        // Pobranie wymiarów obrazu i liczby kanałów
        val batchSize = inputShape[0]
        val height = inputShape[1]
        val width = inputShape[2]
        val channels = if (inputShape.size > 3) inputShape[3] else 1

        // Tworzenie bufora na dane wejściowe
        val bufferSize = batchSize * height * width * channels * if (inputDataType == DataType.FLOAT32) 4 else 1
        val buffer = ByteBuffer.allocateDirect(bufferSize)
        buffer.order(ByteOrder.nativeOrder())

        // Skalowanie obrazu do wymaganego rozmiaru
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

        // Pobranie wartości pikseli z obrazu
        val intValues = IntArray(width * height)
        resizedBitmap.getPixels(intValues, 0, resizedBitmap.width, 0, 0, resizedBitmap.width, resizedBitmap.height)

        // Konwersja pikseli każdego piksela do wartości 0-1 (/255.0f)
        if (inputDataType == DataType.FLOAT32) {
            for (pixelValue in intValues) {
                buffer.putFloat((pixelValue shr 16 and 0xFF) / 255.0f)
                buffer.putFloat((pixelValue shr 8 and 0xFF) / 255.0f)
                buffer.putFloat((pixelValue and 0xFF) / 255.0f)
            }
        } else if (inputDataType == DataType.UINT8) {
            for (pixelValue in intValues) {
                buffer.put((pixelValue shr 16 and 0xFF).toByte())
                buffer.put((pixelValue shr 8 and 0xFF).toByte())
                buffer.put((pixelValue and 0xFF).toByte())
            }
        }

        buffer.rewind()
        return buffer
    }

    override fun onDestroy() {
        super.onDestroy()
        tflite.close()
    }
}
