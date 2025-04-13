package com.example.mushroomapp

import android.content.Context
import android.content.SharedPreferences
import com.example.mushroomapp.data.model.User

class SessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor  = sharedPreferences.edit()

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_NAME = "name"
        private const val KEY_SURNAME = "surname"
    }

    fun createSession(user: User) {
        editor.apply {
            putInt(KEY_USER_ID, user.id)
            putString(KEY_USERNAME, user.username)
            putString(KEY_EMAIL, user.email)
            putString(KEY_NAME, user.name)
            putString(KEY_SURNAME, user.surname)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getInt(KEY_USER_ID, -1) != -1
    }

    fun getUserDetails(): HashMap<String, String?> {
        val user = HashMap<String, String?>()
        user[KEY_USER_ID] = sharedPreferences.getInt(KEY_USER_ID, -1).toString()
        user[KEY_USERNAME] = sharedPreferences.getString(KEY_USERNAME, null)
        user[KEY_EMAIL] = sharedPreferences.getString(KEY_EMAIL, null)
        user[KEY_NAME] = sharedPreferences.getString(KEY_NAME, null)
        user[KEY_SURNAME] = sharedPreferences.getString(KEY_SURNAME, null)

        return user
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt("user_id", -1)
    }

    fun getPassword(): String {
        return sharedPreferences.getString("password", "") ?: ""
    }

    fun updateUserDetails(user: User) {
        editor.apply {
            putString("username", user.username) // Dodaj tę linię
            putString("name", user.name)
            putString("surname", user.surname)
            putString("email", user.email)
            if (user.password.isNotEmpty()) {
                putString("password", user.password)
            }
            apply()
        }
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }

}