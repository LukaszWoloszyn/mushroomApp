package com.example.mushroomapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.Calendar

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mushroom_app.db"
        private const val DATABASE_VERSION = 2

        private const val TABLE_USERS = "users"
        private const val COLUMN_USER_ID = "id"
        private const val COLUMN_USER_USERNAME = "username"
        private const val COLUMN_USER_EMAIL = "email"
        private const val COLUMN_USER_PASSWORD = "password"
        private const val COLUMN_USER_NAME = "name"
        private const val COLUMN_USER_SURNAME = "surname"

        private const val TABLE_TRIPS = "trips"
        private const val COLUMN_TRIP_ID = "id"
        private const val COLUMN_TRIP_USER_ID = "user_id"
        private const val COLUMN_TRIP_TITLE = "title"
        private const val COLUMN_TRIP_DATE = "date"
        private const val COLUMN_TRIP_LOCATION = "location"
        private const val COLUMN_TRIP_FOREST_TYPE = "forest_type"
        private const val COLUMN_TRIP_NOTES = "notes"

        private const val TABLE_TRIP_ITEMS = "trip_items"
        private const val COLUMN_ITEM_ID = "id"
        private const val COLUMN_ITEM_TRIP_ID = "trip_id"
        private const val COLUMN_ITEM_NAME = "name"
        private const val COLUMN_ITEM_IS_CHECKED = "is_checked"

        private const val CREATE_USERS_TABLE = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_USERNAME TEXT NOT NULL UNIQUE,
                $COLUMN_USER_EMAIL TEXT NOT NULL UNIQUE,
                $COLUMN_USER_PASSWORD TEXT NOT NULL,
                $COLUMN_USER_NAME TEXT,
                $COLUMN_USER_SURNAME TEXT
            )
        """

        private const val CREATE_TRIPS_TABLE = """
            CREATE TABLE $TABLE_TRIPS (
                $COLUMN_TRIP_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TRIP_USER_ID INTEGER NOT NULL,
                $COLUMN_TRIP_TITLE TEXT NOT NULL,
                $COLUMN_TRIP_DATE INTEGER NOT NULL,
                $COLUMN_TRIP_LOCATION TEXT NOT NULL,
                $COLUMN_TRIP_FOREST_TYPE TEXT NOT NULL,
                $COLUMN_TRIP_NOTES TEXT,
                FOREIGN KEY ($COLUMN_TRIP_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """

        private const val CREATE_TRIP_ITEMS_TABLE = """
            CREATE TABLE $TABLE_TRIP_ITEMS (
                $COLUMN_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_ITEM_TRIP_ID INTEGER NOT NULL,
                $COLUMN_ITEM_NAME TEXT NOT NULL,
                $COLUMN_ITEM_IS_CHECKED INTEGER DEFAULT 0,
                FOREIGN KEY ($COLUMN_ITEM_TRIP_ID) REFERENCES $TABLE_TRIPS ($COLUMN_TRIP_ID) ON DELETE CASCADE
            )
        """

        private const val TAG = "DatabaseHelper"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Tworzenie bazy danych po raz pierwszy")

        db.execSQL(CREATE_USERS_TABLE)
        db.execSQL(CREATE_TRIPS_TABLE)
        db.execSQL(CREATE_TRIP_ITEMS_TABLE)

        addDefaultUsers(db)
        addDefaultTrip(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "Aktualizacja bazy danych z wersji $oldVersion do $newVersion")

        // Obsługa przejścia z wersji 1 do 2
        if (oldVersion == 1 && newVersion >= 2) {
            try {
                // Tworzenie tylko nowych tabel
                db.execSQL(CREATE_TRIPS_TABLE)
                Log.d(TAG, "Utworzono tabelę trips")

                db.execSQL(CREATE_TRIP_ITEMS_TABLE)
                Log.d(TAG, "Utworzono tabelę trip_items")

                // Dodaj domyślne dane
                addDefaultTrip(db)
            } catch (e: Exception) {
                Log.e(TAG, "Błąd podczas aktualizacji bazy", e)
            }
        }

        // Nie wywołuj onCreate() w onUpgrade() - to może powodować problemy!
        // onCreate(db) - USUŃ TĘ LINIĘ
    }


    private fun addDefaultUsers(db: SQLiteDatabase) {
        Log.d(TAG, "Dodanie przykładowych użytkowników")

        val insertUser1 = """
            INSERT INTO $TABLE_USERS (
                $COLUMN_USER_USERNAME, 
                $COLUMN_USER_EMAIL, 
                $COLUMN_USER_PASSWORD, 
                $COLUMN_USER_NAME, 
                $COLUMN_USER_SURNAME
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
                $COLUMN_USER_USERNAME, 
                $COLUMN_USER_EMAIL, 
                $COLUMN_USER_PASSWORD, 
                $COLUMN_USER_NAME, 
                $COLUMN_USER_SURNAME
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

    fun addDefaultTrip(db: SQLiteDatabase) {
        // Dodawanie przykładowej wycieczki
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, 7) // Wycieczka za tydzień
        val tripDate = calendar.timeInMillis

        val userId = 2 // ID użytkownika (domyślnie 1)

        val tripValues = ContentValues().apply {
            put("user_id", userId)
            put("title", "Wycieczka do Puszczy Białowieskiej")
            put("date", tripDate)
            put("location", "Puszcza Białowieska")
            put("forest_type", "Las mieszany")
            put("notes", "Pamiętać o zabraniu aparatu!")
        }

        val tripId = db.insert("trips", null, tripValues)

        // Dodawanie przedmiotów do zabrania na wycieczkę
        if (tripId != -1L) {
            val defaultItems = listOf(
                "Koszyk wiklinowy",
                "Nóż do grzybów",
                "Woda (2L)",
                "Kanapki",
                "Atlas grzybów",
                "Telefon z nawigacją",
                "Spray na komary"
            )

            for (item in defaultItems) {
                val itemValues = ContentValues().apply {
                    put("trip_id", tripId)
                    put("name", item)
                    put("is_checked", 0) // Domyślnie niezaznaczone
                }
                db.insert("trip_items", null, itemValues)
            }
        }
    }

}