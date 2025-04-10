package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_login, findViewById(R.id.content_frame), true)

        val loginButton = findViewById<Button>(R.id.loginBtn)
        val registerButton = findViewById<TextView>(R.id.registerLink)

        loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

}