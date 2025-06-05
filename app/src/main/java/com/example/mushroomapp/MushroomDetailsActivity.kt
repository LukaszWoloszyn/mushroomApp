package com.example.mushroomapp

import BaseActivity
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.repository.MushroomRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MushroomDetailsActivity : BaseActivity() {

    private lateinit var mushroomRepository: MushroomRepository
    private lateinit var dbHelper: DatabaseHelper

    private var mushroomId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mushroom_details)

        // Konfiguracja toolbara
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // Inicjalizacja repozytorium
        mushroomRepository = MushroomRepository(this)
        dbHelper = DatabaseHelper(this)

        // Pobierz ID grzyba z intentu
        mushroomId = intent.getIntExtra("MUSHROOM_ID", -1)
        if (mushroomId == -1) {
            Toast.makeText(this, "Błąd: nie znaleziono ID grzyba", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Konfiguracja przycisku usuwania
        findViewById<ImageButton>(R.id.delete_button).setOnClickListener {
            showDeleteConfirmationDialog()
        }

        // Załaduj dane grzyba
        loadMushroomDetails()
    }

    private fun loadMushroomDetails() {
        val mushroom = mushroomRepository.getMushroomById(mushroomId)
        if (mushroom == null) {
            Toast.makeText(this, "Nie znaleziono szczegółów grzyba", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Ustawienie danych w widokach
        val imageView = findViewById<ImageView>(R.id.mushroom_image_detail)
        val nameTextView = findViewById<TextView>(R.id.mushroom_name_detail)
        val confidenceTextView = findViewById<TextView>(R.id.mushroom_confidence_detail)
        val dateTextView = findViewById<TextView>(R.id.mushroom_date_detail)
        val descriptionTextView = findViewById<TextView>(R.id.mushroom_description)
        val namePolishTextView = findViewById<TextView>(R.id.mushroom_name_polish)
        val edibilityTextView = findViewById<TextView>(R.id.mushroom_edibility)

        // Wczytaj obraz
        try {
            val bitmap = BitmapFactory.decodeFile(mushroom.imagePath)
            imageView.setImageBitmap(bitmap)
        } catch (e: Exception) {
            imageView.setImageResource(R.color.light_gray)
        }

        // Ustaw teksty
        nameTextView.text = mushroom.mushroomName
        //confidenceTextView.text = "${mushroom.confidence}%"

        // Formatowanie daty
        val dateFormat = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
        dateTextView.text = dateFormat.format(Date(mushroom.date))

        val cleanName = mushroom.mushroomName
            .replace(Regex("\\s*\\(\\d+%\\)"), "") // Usuwa " (71%)" lub "(71%)"
            .replace("_", " ") // Zamienia podkreślniki na spacje
            .trim() // Usuwa białe znaki z początku i końca

        val mushroomInfo = dbHelper.getMushroomInfo(cleanName)
        if (mushroomInfo != null) {
            // OPIS
            val opis = mushroomInfo["opis"] ?: "Brak opisu"
            descriptionTextView.text = opis

            // POLSKA NAZWA
            val polishName = mushroomInfo["nazwa_polska"] ?: ""
            if (polishName.isNotEmpty()) {
                namePolishTextView.text = polishName
                namePolishTextView.visibility = View.VISIBLE
            } else {
                namePolishTextView.visibility = View.GONE
            }

            // JADALNOŚĆ
            val edibility = mushroomInfo["czy_jadalny"] ?: ""
            when (edibility.lowercase()) {
                "tak" -> {
                    edibilityTextView.text = "Grzyb jadalny"
                    edibilityTextView.setTextColor(getColor(R.color.splash_green))
                    edibilityTextView.visibility = View.VISIBLE
                }
                "nie" -> {
                    edibilityTextView.text = "Grzyb NIEJADALNY lub TRUJĄCY!"
                    edibilityTextView.setTextColor(getColor(R.color.red))
                    edibilityTextView.visibility = View.VISIBLE
                }
                else -> {
                    edibilityTextView.visibility = View.GONE
                }
            }

        } else {
            descriptionTextView.text = "Brak opisu dla tego gatunku"
            namePolishTextView.visibility = View.GONE
            edibilityTextView.visibility = View.GONE
        }
    }
    
    private fun showDeleteConfirmationDialog() {
        AlertDialog.Builder(this, R.style.AlertDialogStyle)
            .setTitle("Usuń znalezisko")
            .setMessage("Czy na pewno chcesz usunąć to znalezisko?")
            .setPositiveButton("Usuń") { _, _ -> deleteMushroom() }
            .setNegativeButton("Anuluj", null)
            .show()
    }

    private fun deleteMushroom() {
        val success = mushroomRepository.deleteMushroom(mushroomId)
        if (success) {
            Toast.makeText(this, "Znalezisko zostało usunięte", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Nie udało się usunąć znaleziska", Toast.LENGTH_SHORT).show()
        }
    }

}
