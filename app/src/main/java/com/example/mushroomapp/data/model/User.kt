package com.example.mushroomapp.data.model

data class User(
    val id: Int = 0,
    val username: String,
    val email: String,
    val password: String,
    val name: String? = null,
    val surname: String? = null
)