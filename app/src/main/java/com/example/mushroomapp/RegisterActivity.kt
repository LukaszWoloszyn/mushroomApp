package com.example.mushroomapp

import BaseActivity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mushroomapp.data.model.User
import com.example.mushroomapp.data.repository.UserRepository

class RegisterActivity : BaseActivity() {

    private lateinit var nameInput: EditText
    private lateinit var surnameInput: EditText
    private lateinit var usernameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var confirmPasswordInput: EditText
    private lateinit var registerButton: Button

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutInflater.inflate(R.layout.activity_register, findViewById(R.id.content_frame), true)

        userRepository = UserRepository(this)

        nameInput = findViewById(R.id.name_input)
        surnameInput = findViewById(R.id.surname_input)
        usernameInput = findViewById(R.id.username_input)
        emailInput = findViewById(R.id.email_input)
        passwordInput = findViewById(R.id.password_input)
        confirmPasswordInput = findViewById(R.id.confirm_password_input)
        registerButton = findViewById(R.id.registerBtn)

        registerButton.setOnClickListener {
            registerUser()
        }

        updateNavMenu()
    }

    private fun registerUser() {
        val name = nameInput.text.toString().trim()
        val surname = surnameInput.text.toString().trim()
        val username = usernameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()

        if (!validateInput(name, surname, username, email, password, confirmPassword)) {
            return
        }

        if (userRepository.isEmailUserExists(email)) {
            Toast.makeText(this, "Email użytkownika jest już zajęty", Toast.LENGTH_SHORT).show()
        }

        if (userRepository.isUsernameExists(username)) {
            Toast.makeText(this, "Nazwa użytkownika jest zajęta", Toast.LENGTH_SHORT).show()
        }

        val user = User(
            username = username,
            email = email,
            password = password,
            name = name,
            surname = surname
        )

        val userId = userRepository.createUser(user)

        if (userId > 0) {
            Toast.makeText(this, "Konto utworzone, możesz się zalogować", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Wystąpił błąd, spróbuj ponownie", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(
        name: String,
        surname: String,
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty() || surname.isEmpty() || username.isEmpty() ||
            email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Wszystkie pola są wymagane", Toast.LENGTH_SHORT).show()
            return false
        }

        // Walidacja emaila przy użyciu wbudowanego wzorca Patterns.EMAIL_ADDRESS
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Podaj prawidłowy adres email", Toast.LENGTH_SHORT).show()
            return false
        }

        // Walidacja nazwy użytkownika (min. 3 znaki)
        if (username.length < 3) {
            Toast.makeText(this, "Nazwa użytkownika musi mieć co najmniej 3 znaki", Toast.LENGTH_SHORT).show()
            return false
        }

        // Walidacja hasła (min. 6 znaków)
        if (password.length < 6) {
            Toast.makeText(this, "Hasło musi mieć co najmniej 6 znaków", Toast.LENGTH_SHORT).show()
            return false
        }

        // Sprawdzenie zgodności haseł
        if (password != confirmPassword) {
            Toast.makeText(this, "Hasła nie są zgodne", Toast.LENGTH_SHORT).show()
            return false
        }

        // Wszystkie walidacje przeszły pomyślnie
        return true
    }


}