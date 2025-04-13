package com.example.mushroomapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.model.User


class UserRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val TAG = "UserRepository"

    fun createUser(user: User): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("username", user.username)
            put("email", user.email)
            put("password", user.password)
            put("name", user.name)
            put("surname", user.surname)
        }

        return try {
            val id = db.insert("users", null, values)
            Log.d(TAG, "Utowrzono użytkownika z ID: $id")
            id
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas tworzenia użytkownika", e)
            -1
        } finally {
            //db.close()
        }
    }

    fun getUserById(userId: Int): User? {
        val db = dbHelper.readableDatabase
        var user: User? = null

        val cursor = db.query(
            "users",
            null,
            "id = ?",
            arrayOf(userId.toString()),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            user = cursorToUser(cursor)
        }

        cursor.close()
//        db.close()

        return user
    }

    fun validateUser(usernameOrEmail: String, password: String): User? {
        val db = dbHelper.readableDatabase
        var user: User? = null

        val cursor = db.query(
            "users",
            null,
            "(username = ? OR email = ?) AND password = ?",
            arrayOf(usernameOrEmail, usernameOrEmail, password),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            user = cursorToUser(cursor)
            Log.d(TAG, "Weryfikacja udana")
        } else {
            Log.d(TAG, "Weryfikacja nieudana")
        }

        cursor.close()
        //db.close()

        return user
    }

    private fun cursorToUser(cursor: Cursor): User {
        val idIndex = cursor.getColumnIndex("id")
        val usernameIndex = cursor.getColumnIndex("username")
        val emailIndex = cursor.getColumnIndex("email")
        val passwordIndex = cursor.getColumnIndex("password")
        val nameIndex = cursor.getColumnIndex("name")
        val surnameIndex = cursor.getColumnIndex("surname")

        return User(
            id = if (idIndex >= 0) cursor.getInt(idIndex) else 0,
            username = if (usernameIndex >= 0) cursor.getString(usernameIndex) else "",
            email = if (emailIndex >= 0) cursor.getString(emailIndex) else "",
            password = if (passwordIndex >= 0) cursor.getString(passwordIndex) else "",
            name = if (nameIndex >= 0 && !cursor.isNull(nameIndex)) cursor.getString(nameIndex) else null,
            surname = if (surnameIndex >= 0 && !cursor.isNull(surnameIndex)) cursor.getString(
                surnameIndex
            ) else null
        )
    }

    fun isEmailUserExists(email: String): Boolean {
        val db = dbHelper.readableDatabase

        var cursor = db.query(
            "users",
            arrayOf("id"),
            "email = ?",
            arrayOf(email),
            null,
            null,
            null
        )

        val emailExists = cursor.count > 0
        cursor.close()
        //db.close()

        return emailExists
    }

    fun isUsernameExists(username: String): Boolean {
        val db = dbHelper.readableDatabase

        var cursor = db.query(
            "users",
            arrayOf("id"),
            "username = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        val usernameExists = cursor.count > 0
        cursor.close()
        //db.close()

        return usernameExists
    }

    fun updateUser(user: User): Boolean {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("name", user.name)
            put("surname", user.surname)
            put("email", user.email)
            if (user.password.isNotEmpty()) put("password", user.password)
        }

        return try {
            db.update("users", values, "id=?", arrayOf(user.id.toString())) > 0
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas aktualizacji użytkownika", e)
            false
        } finally {
//            db.close()
        }
    }

    fun deleteUser(userId: Int): Boolean {
        val db = dbHelper.writableDatabase

        return try {
            val rowsDeleted = db.delete(
                "users",
                "id = ?",
                arrayOf(userId.toString())
            )

            rowsDeleted > 0
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas usuwania użytkownika", e)
            false
        } finally {
            //db.close()
        }
    }
}