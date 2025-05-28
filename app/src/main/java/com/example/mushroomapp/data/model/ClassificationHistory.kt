package com.example.mushroomapp.data.model

data class ClassificationHistory(
    val id: Int = 0,
    val userId: Int,
    val imagePath: String,
    val result: String,
    val date: Long = System.currentTimeMillis()
)