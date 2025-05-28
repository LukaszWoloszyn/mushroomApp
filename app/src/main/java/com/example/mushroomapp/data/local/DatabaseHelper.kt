package com.example.mushroomapp.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.mushroomapp.R
import java.util.Calendar

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mushroom_app.db"
        private const val DATABASE_VERSION = 5

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

        private const val TABLE_MUSHROOMS = "mushrooms"
        private const val COLUMN_MUSHROOM_ID = "id"
        private const val COLUMN_MUSHROOM_USER_ID = "user_id"
        private const val COLUMN_MUSHROOM_IMAGE_PATH = "image_path"
        private const val COLUMN_MUSHROOM_NAME = "mushroom_name"
        private const val COLUMN_MUSHROOM_CONFIDENCE = "confidence"
        private const val COLUMN_MUSHROOM_DATE = "date"

        private const val TABLE_CLASSIFICATION_HISTORY = "classification_history"
        private const val COLUMN_HISTORY_ID = "id"
        private const val COLUMN_HISTORY_USER_ID = "user_id"
        private const val COLUMN_HISTORY_IMAGE_PATH = "image_path"
        private const val COLUMN_HISTORY_RESULT = "result"
        private const val COLUMN_HISTORY_DATE = "date"

        private const val TABLE_MUSHROOM_INFO = "mushroom_info"
        private const val COLUMN_MUSHROOM_INFO_NAME = "name"
        private const val COLUMN_MUSHROOM_INFO_NAME_PL = "name_pl"
        private const val COLUMN_MUSHROOM_INFO_DESCRIPTION = "description"
        private const val COLUMN_MUSHROOM_INFO_IS_EDIBLE = "is_edible"

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

        private const val CREATE_MUSHROOMS_TABLE = """
            CREATE TABLE $TABLE_MUSHROOMS (
                $COLUMN_MUSHROOM_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MUSHROOM_USER_ID INTEGER NOT NULL, 
                $COLUMN_MUSHROOM_IMAGE_PATH TEXT NOT NULL,
                $COLUMN_MUSHROOM_NAME TEXT NOT NULL,
                $COLUMN_MUSHROOM_CONFIDENCE INTEGER NOT NULL,
                $COLUMN_MUSHROOM_DATE INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_MUSHROOM_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """

        private const val CREATE_CLASSIFICATION_HISTORY_TABLE = """
            CREATE TABLE $TABLE_CLASSIFICATION_HISTORY (
                $COLUMN_HISTORY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_HISTORY_USER_ID INTEGER NOT NULL,
                $COLUMN_HISTORY_IMAGE_PATH TEXT NOT NULL,
                $COLUMN_HISTORY_RESULT TEXT NOT NULL,
                $COLUMN_HISTORY_DATE INTEGER NOT NULL,
                FOREIGN KEY ($COLUMN_HISTORY_USER_ID) REFERENCES $TABLE_USERS ($COLUMN_USER_ID)
            )
        """

        private const val CREATE_MUSHROOM_INFO_TABLE = """
            CREATE TABLE $TABLE_MUSHROOM_INFO (
                $COLUMN_MUSHROOM_INFO_NAME TEXT PRIMARY KEY,
                $COLUMN_MUSHROOM_INFO_NAME_PL TEXT,
                $COLUMN_MUSHROOM_INFO_DESCRIPTION TEXT,
                $COLUMN_MUSHROOM_INFO_IS_EDIBLE TEXT
    )
"""


        private const val TAG = "DatabaseHelper"
    }

    override fun onCreate(db: SQLiteDatabase) {
        Log.d(TAG, "Tworzenie bazy danych po raz pierwszy")

        db.execSQL(CREATE_USERS_TABLE)
        db.execSQL(CREATE_TRIPS_TABLE)
        db.execSQL(CREATE_TRIP_ITEMS_TABLE)
        db.execSQL(CREATE_MUSHROOMS_TABLE)
        db.execSQL(CREATE_CLASSIFICATION_HISTORY_TABLE)

        executeSqlFile(db, R.raw.mushroom_data)

        importMushroomInfo(db)
        addDefaultUsers(db)
        addDefaultTrip(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(TAG, "Aktualizacja bazy danych z wersji $oldVersion do $newVersion")

        // Obsługa przejścia z wersji 1 do 2
        if (oldVersion < 5) {
            try {
                db.execSQL(CREATE_MUSHROOMS_TABLE)
                db.execSQL(CREATE_CLASSIFICATION_HISTORY_TABLE)
                executeSqlFile(db, R.raw.mushroom_data)
                importMushroomInfo(db)
                Log.d(TAG, "Utworzono tabelę classification_history")
            } catch (e: Exception) {
                Log.e(TAG, "Błąd podczas aktualizacji bazy", e)
            }
        }
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

    private fun importMushroomInfo(db: SQLiteDatabase) {
        val insertStatement = "INSERT INTO $TABLE_MUSHROOM_INFO ($COLUMN_MUSHROOM_INFO_NAME, $COLUMN_MUSHROOM_INFO_NAME_PL, $COLUMN_MUSHROOM_INFO_DESCRIPTION, $COLUMN_MUSHROOM_INFO_IS_EDIBLE) VALUES (?, ?, ?, ?)"
        val statement = db.compileStatement(insertStatement)

        db.beginTransaction()
        try {
            // Pierwsza pozycja
            statement.bindString(1, "Boletus aereus")
            statement.bindString(2, "Borowik brązowy")
            statement.bindString(3, "Kapelusz o średnicy 5-25 cm, początkowo półkulisty, później wypukły, ciemnobrązowy do czarnobrązowego. Powierzchnia sucha, czasem delikatnie zamszowa. Rurki białawe, później żółtozielone, nie sinieją po uszkodzeniu. Trzon pękaty, brązowy, z delikatną siateczką w górnej części. Miąższ biały, nie zmienia barwy po przekrojeniu. Występuje w lasach liściastych, szczególnie pod dębami i bukami, preferuje cieplejsze regiony.")
            statement.bindString(4, "tak")
            statement.executeInsert()
            statement.clearBindings()

            // Dodaj więcej pozycji - możesz przygotować plik SQL i go odczytać

            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        Log.d(TAG, "Zaimportowano dane o grzybach")
    }

    // W klasie DatabaseHelper lub osobnym repozytorium:
    fun getMushroomInfo(mushroomName: String): Map<String, String>? {
        val db = readableDatabase
        val result = mutableMapOf<String, String>()

        val cursor = db.query(
            TABLE_MUSHROOM_INFO,
            null,
            "$COLUMN_MUSHROOM_INFO_NAME LIKE ?",
            arrayOf("%$mushroomName%"),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            val nazwaIndex = cursor.getColumnIndex(COLUMN_MUSHROOM_INFO_NAME)
            val nazwaPolskaIndex = cursor.getColumnIndex(COLUMN_MUSHROOM_INFO_NAME_PL)
            val opisIndex = cursor.getColumnIndex(COLUMN_MUSHROOM_INFO_DESCRIPTION)
            val czyJadalnyIndex = cursor.getColumnIndex(COLUMN_MUSHROOM_INFO_IS_EDIBLE)

            if (nazwaIndex >= 0) result["nazwa"] = cursor.getString(nazwaIndex)
            if (nazwaPolskaIndex >= 0) result["nazwa_polska"] = cursor.getString(nazwaPolskaIndex)
            if (opisIndex >= 0) result["opis"] = cursor.getString(opisIndex)
            if (czyJadalnyIndex >= 0) result["czy_jadalny"] = cursor.getString(czyJadalnyIndex)

            cursor.close()
            return result
        }

        cursor.close()
        return null
    }

    private fun executeSqlFile(db: SQLiteDatabase, resourceId: Int) {
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val reader = inputStream.bufferedReader()
            val buffer = StringBuilder()

            var line: String?
            while (reader.readLine().also { line = it } != null) {
                // Pomijamy komentarze i puste linie
                if (line!!.startsWith("--") || line!!.isEmpty()) continue

                buffer.append(line)

                // Jeśli linia kończy się średnikiem, wykonujemy zapytanie
                if (line!!.trim().endsWith(";")) {
                    db.execSQL(buffer.toString())
                    buffer.clear()
                }
            }

            reader.close()
            Log.d(TAG, "Pomyślnie wczytano dane z pliku SQL")
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas wczytywania danych z pliku SQL", e)
        }
    }
}