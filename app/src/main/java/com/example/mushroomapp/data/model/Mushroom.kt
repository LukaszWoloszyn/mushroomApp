package com.example.mushroomapp.data.model

data class Mushroom(
    val id: Int = 0,
    val userId: Int,
    val imagePath: String,
    val mushroomName: String,
    val confidence: Int,
    val date: Long = System.currentTimeMillis()
)