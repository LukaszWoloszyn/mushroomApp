package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mushroomapp.data.repository.UserRepository
import com.google.android.material.card.MaterialCardView
import de.hdodenhof.circleimageview.CircleImageView

class ProfileActivity : BaseActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var profileImage: CircleImageView
    private lateinit var usernameText: TextView
    private lateinit var emailText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutInflater.inflate(R.layout.activity_profile, findViewById(R.id.content_frame), true)

        userRepository = UserRepository(this)

        profileImage = findViewById(R.id.profile_image)
        usernameText = findViewById(R.id.username_text)
        emailText = findViewById(R.id.email_text)

        loadUserData()

        setupClickListener()
    }

    private fun loadUserData() {
        val userDetails = sessionManager.getUserDetails()
        val userId = userDetails["user_id"]?.toIntOrNull() ?: -1

        if (userId != -1) {
            val username = userDetails["username"] ?: "Użytkownik"
            val name = userDetails["name"]
            val surname = userDetails["surname"]
            val email = userDetails["email"] ?: ""

            val displayName = if (!name.isNullOrEmpty() && !surname.isNullOrEmpty()) {
                "$name $surname"
            } else {
                username
            }

            usernameText.text = displayName
            emailText.text = email

        } else {
            Toast.makeText(this, "Błąd podczas ładowania profilu", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupClickListener() {
        findViewById<MaterialCardView>(R.id.edit_profile_card).setOnClickListener{
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        findViewById<MaterialCardView>(R.id.my_mushrooms_card).setOnClickListener{
            // TODO
        }

        findViewById<MaterialCardView>(R.id.classification_history_card).setOnClickListener{
            // TODO
        }

        findViewById<MaterialCardView>(R.id.delete_account_card).setOnClickListener{
            showDeleteAccountDialog()
        }
    }

    private fun showDeleteAccountDialog() {
        AlertDialog.Builder(this)
            .setTitle("Usuń konto")
            .setMessage("Czy na pewno chcesz usunąc konto?")
            .setPositiveButton("Usuń") { _, _ ->
            deleteUserAccount()
        }
            .setNegativeButton("Anuluj", null)
            .show()
    }

    private fun deleteUserAccount() {
        val userDetails = sessionManager.getUserDetails()
        val userId = userDetails["user_id"]?.toIntOrNull() ?: -1

        if (userId != -1) {
            val success = true

            if (success) {
                sessionManager.logout()
                Toast.makeText(this, "Konto zostało usunięte", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Wystąpił błąd podczas usuwania konta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadUserData()
    }


}