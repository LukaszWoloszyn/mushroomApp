package com.example.mushroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.Trip
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TripsAdapter(
    private val trips: List<Trip>,
    private val onTripClick: (Trip) -> Unit
) : RecyclerView.Adapter<TripsAdapter.TripViewHolder>() {

    class TripViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.trip_title)
        val dateTextView: TextView = view.findViewById(R.id.trip_date)
        val locationTextView: TextView = view.findViewById(R.id.trip_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip, parent, false)
        return TripViewHolder(view)
    }

    override fun onBindViewHolder(holder: TripViewHolder, position: Int) {
        val trip = trips[position]

        holder.titleTextView.text = trip.title

        // Formatowanie daty
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        holder.dateTextView.text = dateFormat.format(Date(trip.date))

        holder.locationTextView.text = trip.location

        // Obsługa kliknięcia
        holder.itemView.setOnClickListener {
            onTripClick(trip)
        }
    }

    override fun getItemCount() = trips.size
}
