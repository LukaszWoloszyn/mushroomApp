package com.example.mushroomapp

import BaseActivity
import android.os.Bundle

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_register, findViewById(R.id.content_frame), true)


    }


}