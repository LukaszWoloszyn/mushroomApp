package com.example.mushroomapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.Trip
import com.example.mushroomapp.data.model.TripItem
import com.example.mushroomapp.data.repository.TripRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TripDetailsActivity : AppCompatActivity() {

    private lateinit var tripRepository: TripRepository
    private lateinit var trip: Trip
    private val tripItems = mutableListOf<TripItem>()
    private lateinit var itemsAdapter: TripItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_details)

        // Inicjalizacja repozytorium
        tripRepository = TripRepository(this)

        // Pobieranie ID wyprawy z intentu
        val tripId = intent.getIntExtra("TRIP_ID", -1)

        if (tripId == -1) {
            Toast.makeText(this, "Błąd: nieprawidłowe ID wyprawy", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Pobieranie szczegółów wyprawy
        trip = tripRepository.getTripById(tripId) ?: run {
            Toast.makeText(this, "Nie znaleziono wyprawy", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Inicjalizacja widoków
        setupViews()

        // Pobieranie listy przedmiotów
        loadTripItems(tripId)

        // Konfiguracja przycisków
        setupButtons(tripId)
    }

    private fun setupViews() {
        findViewById<TextView>(R.id.trip_title).text = trip.title

        // Formatowanie daty
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        findViewById<TextView>(R.id.trip_date).text = dateFormat.format(Date(trip.date))

        findViewById<TextView>(R.id.trip_location).text = trip.location
        findViewById<TextView>(R.id.trip_forest_type).text = trip.forestType

        // Konfiguracja RecyclerView dla listy przedmiotów
        val recyclerView = findViewById<RecyclerView>(R.id.items_recycler_view)
        itemsAdapter = TripItemsAdapter(tripItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemsAdapter
    }

    private fun loadTripItems(tripId: Int) {
        val items = tripRepository.getTripItems(tripId)
        tripItems.clear()
        tripItems.addAll(items)
        itemsAdapter.notifyDataSetChanged()
    }

    private fun setupButtons(tripId: Int) {
        // Przycisk edycji
        findViewById<Button>(R.id.edit_trip_button).setOnClickListener {
            val intent = Intent(this, AddEditTripActivity::class.java).apply {
                putExtra("TRIP_ID", tripId)
            }
            startActivity(intent)
        }

        // Przycisk usuwania
        findViewById<Button>(R.id.delete_trip_button).setOnClickListener {
            confirmDeleteTrip(tripId)
        }
    }

    private fun confirmDeleteTrip(tripId: Int) {
        AlertDialog.Builder(this, R.style.AlertDialogStyle)
            .setTitle("Usuń wyprawę")
            .setMessage("Czy na pewno chcesz usunąć tę wyprawę?")
            .setPositiveButton("Usuń") { _, _ ->
                deleteTrip(tripId)
            }
            .setNegativeButton("Anuluj", null)
            .show()
    }

    private fun deleteTrip(tripId: Int) {
        if (tripRepository.deleteTrip(tripId)) {
            Toast.makeText(this, "Wyprawa została usunięta", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Błąd podczas usuwania wyprawy", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Odświeżenie danych przy powrocie z edycji
        val tripId = intent.getIntExtra("TRIP_ID", -1)
        if (tripId != -1) {
            tripRepository.getTripById(tripId)?.let {
                trip = it
                setupViews()
                loadTripItems(tripId)
            }
        }
    }
}
