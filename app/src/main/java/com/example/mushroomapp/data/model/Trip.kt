package com.example.mushroomapp.data.model

data class Trip(
    val id: Int = 0,
    val userId: Int,
    val title: String,
    val date: Long,
    val location: String,
    val forestType: String,
    val notes: String? = null
)
