package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mushroomapp.data.repository.UserRepository

class LoginActivity : BaseActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_login, findViewById(R.id.content_frame), true)

        userRepository = UserRepository(this)

        val emailInput = findViewById<TextView>(R.id.email)
        val passwordInput = findViewById<TextView>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginBtn)
        val registerButton = findViewById<TextView>(R.id.registerLink)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
            } else {
                val user = userRepository.validateUser(email, password)

                if (user != null) {
                    Toast.makeText(this, "Witaj! ${user.name ?: user.username}", Toast.LENGTH_SHORT).show()

                    sessionManager.createSession(user)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Nieprawidłowe dane logowania", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        updateNavMenu()

    }

//    private fun saveUserSession(user: User) {
//        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
//
//        with(sharedPreferences.edit()) {
//            putInt("user_id", user.id)
//            putString("username", user.username)
//            putString("email", user.email)
//            putString("name", user.name)
//            apply() // Zapisuje zmiany asynchronicznie
//        }
//    }

}