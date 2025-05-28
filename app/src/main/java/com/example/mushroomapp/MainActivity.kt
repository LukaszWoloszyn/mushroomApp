package com.example.mushroomapp

import BaseActivity
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.model.ClassificationHistory
import com.example.mushroomapp.data.model.Mushroom
import com.example.mushroomapp.data.repository.ClassificationHistoryRepository
import com.example.mushroomapp.data.repository.MushroomRepository
import com.example.mushroomapp.utils.ImageUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel
import java.util.logging.Handler

class MainActivity : BaseActivity() {

    private lateinit var tflite: Interpreter
    private lateinit var labels: List<String>
    private val PICK_IMAGE_REQUEST = 1
    private val REQUEST_IMAGE_CAPTURE = 2

    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView
    private lateinit var saveButton: Button

    private lateinit var mushroomRepository: MushroomRepository
    private lateinit var historyRepository: ClassificationHistoryRepository

    private lateinit var mushroomInfoCard: CardView
    private lateinit var mushroomNameTextView: TextView
    private lateinit var mushroomEdibleTextView: TextView
    private lateinit var mushroomDescriptionTextView: TextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_main, findViewById(R.id.content_frame), true)

        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)
        saveButton = findViewById(R.id.save_mushroom_button)

        mushroomRepository = MushroomRepository(this)
        historyRepository = ClassificationHistoryRepository(this)

        labels = assets.open("labels.txt").bufferedReader().use {
            it.readLines()
        }

        // Ładowanie modelu
        try {
            tflite = Interpreter(loadModelFile("final.tflite"))
            //Toast.makeText(this, "Model załadowadowany poprawnie", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("TFLite", "Błąd podczas ładowania modelu", e)
            //Toast.makeText(this, "Nie udało się załawodać modelu", Toast.LENGTH_SHORT).show()
            return
        }

        mushroomInfoCard = findViewById(R.id.mushroom_info_card)
        mushroomNameTextView = findViewById(R.id.mushroom_name_textview)
        mushroomEdibleTextView = findViewById(R.id.mushroom_edible_textview)
        mushroomDescriptionTextView = findViewById(R.id.mushroom_description_textview)
        dbHelper = DatabaseHelper(this)



        updateNavMenu()
        setupBottomNavigation()
        setupCameraButton()
        setupSaveButton()

        //resetDatabase()
    }

    private fun setupCameraButton() {
        val cameraButton = findViewById<FloatingActionButton>(R.id.cameraButton)
        cameraButton.setOnClickListener {
            dispatchTakePictureIntent()
        }
    }

    private fun setupSaveButton() {
        saveButton.setOnClickListener {
            saveCurrentMushroom()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            } ?: run {
                Toast.makeText(this, "Brak aplikacji aparatu", Toast.LENGTH_SHORT).show()
            }
        }
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
                R.id.profile -> {
                    // Pobierz bitmap z ImageView
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
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

        when (requestCode) {
            PICK_IMAGE_REQUEST -> {
                if (resultCode == RESULT_OK) {
                    data?.data?.let { uri ->
                        try {
                            val inputStream = contentResolver.openInputStream(uri)
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            imageView.setImageBitmap(bitmap)
                            processAndClassifyImage(bitmap)
                        } catch (e: Exception) {
                            Log.e("ImageSelection", "Błąd podczas wczytywania obrazu", e)
                            Toast.makeText(this, "Nie udało się wczytać obrazu", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == RESULT_OK) {
                    val imageBitmap = data?.extras?.get("data") as? Bitmap
                    imageBitmap?.let {
                        imageView.setImageBitmap(it)
                        processAndClassifyImage(it)
                    }
                }
            }
        }
    }

    private fun processAndClassifyImage(bitmap: Bitmap) {
        val inputBuffer = preprocessImage(bitmap)
        val prediction = classifyImage(inputBuffer)
        resultTextView.text = prediction
    }

    // Przekazywanie przetworzonego obrazu do mogelu
    private fun classifyImage(inputBuffer: ByteBuffer): String {
        try {
            // Tworzenie tablicy wyjściowej
            val outputSize = labels.size
            val outputArray = Array(1) { FloatArray(outputSize) }

            // Uruchomienie modelu
            tflite.run(inputBuffer, outputArray)

            // Pobranie prawdopodobieństw
            val probabilities = outputArray[0]

            // Zastosuj kalibrację temperatury (zwiększa pewność)
            val temperature = 0.3f  // Niższa wartość = wyższa pewność (0.2-0.5)
            val calibratedProbs = applyTemperatureCalibration(probabilities, temperature)

            // Sortowanie indeksów po kalibracji
            val sortedIndices = calibratedProbs.indices.sortedByDescending { calibratedProbs[it] }

            // Logowanie dla debugowania
            sortedIndices.take(3).forEach { index ->
                Log.d("TFLite", "Top 3 - Klasa: ${labels[index]}, " +
                        "Kalibrowane prawdopodobieństwo: ${calibratedProbs[index] * 100}%")
            }

            // Top 3 indeksy
            val top3Indices = sortedIndices.take(3)

            // Normalizacja top-3 do 100%
            val top3Sum = top3Indices.sumOf { calibratedProbs[it].toDouble() }.toFloat()
            val normalizedProbs = top3Indices.associateWith {
                (calibratedProbs[it] / top3Sum) * 100f
            }

            // Przygotowanie tekstu wyniku
            val threshold = 0.55f  // Obniżony próg dla kalibrowanych wartości
            val maxIdx = sortedIndices[0]
            val maxProbability = calibratedProbs[maxIdx]

            val resultText = if (maxProbability < threshold) {
                // Ukryj kartę informacyjną, gdy nie rozpoznano grzyba
                runOnUiThread {
                    mushroomInfoCard.visibility = View.GONE
                    saveButton.visibility = View.GONE
                }
                "Nie rozpoznano żadnego grzyba."
            } else {
                // Szczegóły z znormalizowanymi wartościami procentowymi
                val resultDetails = top3Indices.mapIndexed { i, idx ->
                    val normalizedPercentage = normalizedProbs[idx]?.toInt() ?: 0
                    "${i+1}. ${labels[idx]}: ${normalizedPercentage}%"
                }.joinToString("\n")

                val mushroomName = labels[maxIdx]

                // Pobierz i wyświetl informacje o grzybie
                displayMushroomInfo(mushroomName)

                // Zwróć wynik ze znormalizowaną wartością procentową
                val topPercentage = normalizedProbs[maxIdx]?.toInt() ?: 0
                "Rozpoznano: $mushroomName ($topPercentage%)\n\nSzczegóły (Top 3):\n$resultDetails"
            }

            // Obsługa wyników klasyfikacji
            val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap
            if (bitmap != null && maxProbability >= threshold) {
                onClassificationComplete(resultText, bitmap)
                runOnUiThread {
                    saveButton.visibility = View.VISIBLE
                }
            } else {
                runOnUiThread {
                    saveButton.visibility = View.GONE
                }
            }

            return resultText
        } catch (e: Exception) {
            Log.e("TFLite", "Błąd podczas klasyfikacji", e)
            return "Błąd klasyfikacji: ${e.message}"
        }
    }

    // Dodaj tę metodę pomocniczą poniżej funkcji classifyImage
    private fun applyTemperatureCalibration(probabilities: FloatArray, temperature: Float): FloatArray {
        val calibrated = FloatArray(probabilities.size)

        // Podnieś do potęgi 1/temperature
        var sum = 0f
        for (i in probabilities.indices) {
            calibrated[i] = Math.pow(probabilities[i].toDouble(), 1.0 / temperature).toFloat()
            sum += calibrated[i]
        }

        // Normalizuj, aby suma = 1
        if (sum > 0) {
            for (i in calibrated.indices) {
                calibrated[i] /= sum
            }
        }

        return calibrated
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
        val imgSize = 224
        val byteBuffer = ByteBuffer.allocateDirect(4 * imgSize * imgSize * 3)
        byteBuffer.order(ByteOrder.nativeOrder())

        // resize
        val scaled = Bitmap.createScaledBitmap(bitmap, imgSize, imgSize, true)
        // pobierz RGBA wyciągnij R,G,B
        val intVals = IntArray(imgSize * imgSize)
        scaled.getPixels(intVals, 0, imgSize, 0, 0, imgSize, imgSize)

        for (pixel in intVals) {
            // każde skalowanie dzielimy przez 255f
            byteBuffer.putFloat(((pixel shr 16) and 0xFF) / 255.0f)
            byteBuffer.putFloat(((pixel shr  8) and 0xFF) / 255.0f)
            byteBuffer.putFloat(( pixel        and 0xFF) / 255.0f)
        }
        byteBuffer.rewind()
        return byteBuffer
    }


    private fun saveCurrentMushroom() {
        val bitmap = (imageView.drawable as? BitmapDrawable)?.bitmap ?: return
        val result = resultTextView.text.toString()

        // Zapisz obraz
        val imagePath = ImageUtils.saveImageToInternalStorage(this, bitmap)

        // Wyciągnij nazwę i pewność
        val mushroomName = extractMushroomName(result)
        val confidence = extractConfidence(result)

        // Zapisz do bazy danych
        val mushroom = Mushroom(
            userId = sessionManager.getUserId(),
            imagePath = imagePath,
            mushroomName = mushroomName,
            confidence = confidence
        )

        mushroomRepository.saveMushroom(mushroom)
        Toast.makeText(this, "Znalezisko zapisane!", Toast.LENGTH_SHORT).show()
    }

    // Pomocnicze metody
    private fun extractMushroomName(result: String): String {
        if (result.contains("Rozpoznano:")) {
            val start = result.indexOf("Rozpoznano:") + 12
            val end = result.indexOf("\n", start)
            return if (end > start) result.substring(start, end).trim() else "Nieznany"
        }
        return "Nieznany"
    }

    private fun extractConfidence(result: String): Int {
        val pattern = "(\\d+)%".toRegex()
        val match = pattern.find(result)
        return match?.groupValues?.get(1)?.toIntOrNull() ?: 0
    }

    private fun onClassificationComplete(result: String, bitmap: Bitmap) {
        resultTextView.text = result

        // Zapisz do historii klasyfikacji
        val imagePath = ImageUtils.saveImageToInternalStorage(this, bitmap)
        val history = ClassificationHistory(
            userId = sessionManager.getUserId(),
            imagePath = imagePath,
            result = result
        )

        historyRepository.saveClassification(history)
    }

    private fun displayMushroomInfo(mushroomName: String) {
        val mushroomInfo = dbHelper.getMushroomInfo(mushroomName)

        if (mushroomInfo != null) {
            // Ustaw tekst w widokach
            val nazwa = mushroomInfo["nazwa"] ?: ""
            val nazwaPolska = mushroomInfo["nazwa_polska"] ?: "Brak polskiej nazwy"
            val opis = mushroomInfo["opis"] ?: "Brak opisu"
            val czyJadalny = mushroomInfo["czy_jadalny"] ?: "nieznane"

            // Formatowanie informacji o jadalności
            val jadalnyText = when (czyJadalny.toLowerCase()) {
                "tak" -> "Grzyb jadalny"
                "nie" -> "Grzyb NIEJADALNY lub TRUJĄCY!"
                else -> "Jadalność: $czyJadalny"
            }

            // Ustaw tekst w widokach
            mushroomNameTextView.text = "$nazwa ($nazwaPolska)"
            mushroomEdibleTextView.text = jadalnyText
            mushroomDescriptionTextView.text = opis

            // Ustaw kolor tekstu o jadalnoĹci
            when (czyJadalny.toLowerCase()) {
                "tak" -> mushroomEdibleTextView.setTextColor(ContextCompat.getColor(this, R.color.nav))
                "nie" -> mushroomEdibleTextView.setTextColor(ContextCompat.getColor(this, R.color.purple_700))
                else -> mushroomEdibleTextView.setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
            }

            // Pokaż kartę z informacjami
            mushroomInfoCard.visibility = View.VISIBLE
        } else {
            // Brak informacji o grzybie
            mushroomInfoCard.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tflite.close()
    }

    private fun resetDatabase() {
        val dbPath = getDatabasePath("mushroom_app.db")
        if (dbPath.exists()) {
            dbPath.delete()
            Log.d("MainActivity", "Usunięcie bazy danych")
        }
        // To wymusi ponowne utworzenie bazy
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase
        db.close()
    }
}