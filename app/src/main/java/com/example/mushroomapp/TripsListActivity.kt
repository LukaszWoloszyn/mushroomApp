package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.Trip
import com.example.mushroomapp.data.repository.TripRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TripsListActivity : BaseActivity() {

    private lateinit var tripRepository: TripRepository
    private lateinit var tripsAdapter: TripsAdapter
    private val trips = mutableListOf<Trip>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_trips_list, findViewById(R.id.content_frame), true)

        // Inicjalizacja repozytoriów
        tripRepository = TripRepository(this)

        // Konfiguracja RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.trips_recycler_view)
        tripsAdapter = TripsAdapter(trips) { trip ->
            // Obsługa kliknięcia na wyprawę
            val intent = Intent(this, TripDetailsActivity::class.java)
            intent.putExtra("TRIP_ID", trip.id)
            startActivity(intent)
        }
        recyclerView.adapter = tripsAdapter

        // Przycisk dodawania nowej wyprawy
        findViewById<FloatingActionButton>(R.id.add_trip_fab).setOnClickListener {
            startActivity(Intent(this, AddEditTripActivity::class.java))
        }

        // Załadowanie wypraw
        loadTrips()
        updateNavMenu()
    }

    override fun onResume() {
        super.onResume()
        loadTrips() // Odświeżenie danych przy powrocie do ekranu
    }

    private fun loadTrips() {
        val userId = sessionManager.getUserId()
        if (userId != -1) {
            val userTrips = tripRepository.getTripsForUser(userId)
            trips.clear()
            trips.addAll(userTrips)
            tripsAdapter.notifyDataSetChanged()

            // Pokazywanie komunikatu o braku wypraw jeśli lista jest pusta
            findViewById<TextView>(R.id.empty_view).visibility =
                if (trips.isEmpty()) View.VISIBLE else View.GONE
        }
    }
}