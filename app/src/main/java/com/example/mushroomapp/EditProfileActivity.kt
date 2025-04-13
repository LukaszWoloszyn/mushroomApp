package com.example.mushroomapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mushroomapp.data.model.User
import com.example.mushroomapp.data.repository.UserRepository

class EditProfileActivity : AppCompatActivity() {

    private lateinit var nameInput: EditText
    private lateinit var surnameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var currentPasswordInput: EditText
    private lateinit var newPasswordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var saveButton: Button

    private lateinit var userRepository: UserRepository
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        userRepository = UserRepository(this)
        sessionManager = SessionManager(this)

        nameInput = findViewById(R.id.name_input)
        surnameInput = findViewById(R.id.surname_input)
        emailInput = findViewById(R.id.email_input)
        currentPasswordInput = findViewById(R.id.current_password_input)
        newPasswordInput = findViewById(R.id.new_password_input)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        saveButton = findViewById(R.id.save_button)

        loadUserData()

        saveButton.setOnClickListener {
            saveChanges()
        }

        // Przycisk powrotu
        toolbar.setNavigationOnClickListener {
            finish() // Zamyka aktywność i wraca do poprzedniego ekranu

        }
    }

    private fun loadUserData() {
        val userDetails = sessionManager.getUserDetails()
        nameInput.setText(userDetails["name"] ?: "")
        surnameInput.setText(userDetails["surname"] ?: "")
        emailInput.setText(userDetails["email"] ?: "")
    }

    private fun saveChanges() {
        val name = nameInput.text.toString().trim()
        val surname = surnameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val currentPassword = currentPasswordInput.text.toString()
        val newPassword = newPasswordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Imię i email są wymagane", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword.isNotEmpty() || confirmPassword.isNotEmpty()) {
            if (newPassword != confirmPassword) {
                Toast.makeText(this, "Hasła nie są zgodne", Toast.LENGTH_SHORT).show()
                return
            }

            if (!validateCurrentPassword(currentPassword)) {
                Toast.makeText(this, "Nieprawidłowe aktualne hasło", Toast.LENGTH_SHORT).show()
                return
            }
        }

        val updatedUser = User(
            id = sessionManager.getUserId(),
            username = sessionManager.getUserDetails()["username"] ?: "", // Pobierz username z sesji
            email = email,
            password = if (newPassword.isNotEmpty()) newPassword else sessionManager.getPassword(),
            name = name,
            surname = surname
        )


        if (userRepository.updateUser(updatedUser)) {
            sessionManager.updateUserDetails(updatedUser)
            Toast.makeText(this, "Zmiany zostały zapisane", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Wystąpił błąd podczas zapisywania zmian", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateCurrentPassword(password: String): Boolean {
        val currentUser = userRepository.getUserById(sessionManager.getUserId())
        return currentUser?.password == password
    }

}