package com.example.mushroomapp.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mushroom_app.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_SURNAME = "surname"

        private const val TAG = "DatabaseHelper"

    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Tworzenie bazy danych poraz pierwszy")

        val CREATE_USERS_TABLE = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER  PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT NOT NULL UNIQUE,
                $COLUMN_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_NAME TEXT,
                $COLUMN_SURNAME TEXT
            )
        """.trimIndent()

        Log.d(TAG, "Tworzenie tabeli users")
        db.execSQL(CREATE_USERS_TABLE)

        addDefaultUsers(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "Aktualizacja bazy danych z wersji $oldVersion do $newVersion")

        //db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")

        onCreate(db)
    }

    private fun addDefaultUsers(db: SQLiteDatabase) {
        Log.d(TAG, "Dodanie przykładowych użytkowników")

        val insertUser1 = """
            INSERT INTO $TABLE_USERS (
                $COLUMN_USERNAME, 
                $COLUMN_EMAIL, 
                $COLUMN_PASSWORD, 
                $COLUMN_NAME, 
                $COLUMN_SURNAME
            )
            VALUES (
                'janek', 
                'jan@example.com', 
                'haslo123', 
                'Jan', 
                'Kowalski'
            )
        """.trimIndent()

        val insertUser2 = """
            INSERT INTO $TABLE_USERS (
                $COLUMN_USERNAME, 
                $COLUMN_EMAIL, 
                $COLUMN_PASSWORD, 
                $COLUMN_NAME, 
                $COLUMN_SURNAME
            )
            VALUES (
                'anka', 
                'anna@example.com', 
                'haslo123', 
                'Anna', 
                'Nowak'
            )
        """.trimIndent()

        try {
            db.execSQL(insertUser1)
            Log.d(TAG, "Dodano użytkownika Janek")

            db.execSQL(insertUser2)
            Log.d(TAG, "Dodano użytkownika Anna")
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas dodawania", e)
        }
    }
}