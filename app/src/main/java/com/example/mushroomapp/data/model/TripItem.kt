package com.example.mushroomapp.data.model

data class TripItem(
    val id: Int = 0,
    val tripId: Int,
    val name: String,
    val isChecked: Boolean = false
)
