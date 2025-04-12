package com.example.mushroomapp

import BaseActivity
import android.os.Bundle
import com.example.mushroomapp.data.repository.UserRepository

class ProfileActivity : BaseActivity() {

    private lateinit var userRepository: UserRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_profile, findViewById(R.id.content_frame), true)

        userRepository = UserRepository(this)
    }


}