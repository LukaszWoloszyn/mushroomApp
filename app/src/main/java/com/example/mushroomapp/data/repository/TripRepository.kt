package com.example.mushroomapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.model.Trip
import com.example.mushroomapp.data.model.TripItem

class TripRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private val TAG = "Trip Repository"

    fun createTrip(trip: Trip): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_id", trip.userId)
            put("title", trip.title)
            put("date", trip.date)
            put("location", trip.location)
            put("forest_type", trip.forestType)
            put("notes", trip.notes)
        }

        return try {
            db.insert("trips", null, values)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas dodawania wyprawy", e)
            -1
        } finally {
            //db.close()
        }
    }

    fun getTripsForUser(userId: Int): List<Trip> {
        val trips = mutableListOf<Trip>()
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            "trips",
            null,
            "user_id = ?",
            arrayOf(userId.toString()),
            null, null,
            "date DESC" // Sortujemy od najnowszej daty
        )

        while (cursor.moveToNext()) {
            trips.add(cursorToTrip(cursor))
        }

        cursor.close()
        //db.close()

        return trips
    }

    fun addTripItem(item: TripItem): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("trip_id", item.tripId)
            put("name", item.name)
            put("is_checked", if (item.isChecked) 1 else 0)
        }

        return try {
            db.insert("trip_items", null, values)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas dodawania przedmiotu", e)
            -1
        } finally {
            //db.close()
        }
    }

    fun updateTrip(trip: Trip): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_id", trip.userId)
            put("title", trip.title)
            put("date", trip.date)
            put("location", trip.location)
            put("forest_type", trip.forestType)
            put("notes", trip.notes)
        }

        return try {
            val rowsUpdated = db.update(
                "trips",
                values,
                "id = ?",
                arrayOf(trip.id.toString())
            )
            rowsUpdated > 0
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas aktualizacji wyprawy", e)
            false
        } finally {
            //db.close()
        }
    }

    fun deleteAllItemsForTrip(tripId: Int): Boolean {
        val db = dbHelper.writableDatabase

        return try {
            val rowsDeleted = db.delete(
                "trip_items",
                "trip_id = ?",
                arrayOf(tripId.toString())
            )
            // Zwracamy true nawet jeśli nie usunięto żadnych elementów
            // (mogło ich po prostu nie być)
            true
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas usuwania przedmiotów wyprawy", e)
            false
        } finally {
            //db.close()
        }
    }

    fun getTripById(tripId: Int): Trip? {
        val db = dbHelper.readableDatabase
        var trip: Trip? = null

        val cursor = db.query(
            "trips",
            null,
            "id = ?",
            arrayOf(tripId.toString()),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            trip = Trip(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
                date = cursor.getLong(cursor.getColumnIndexOrThrow("date")),
                location = cursor.getString(cursor.getColumnIndexOrThrow("location")),
                forestType = cursor.getString(cursor.getColumnIndexOrThrow("forest_type")),
                notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"))
            )
        }

        cursor.close()
        //db.close()

        return trip
    }

    fun getTripItems(tripId: Int): List<TripItem> {
        val items = mutableListOf<TripItem>()
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            "trip_items",
            null,
            "trip_id = ?",
            arrayOf(tripId.toString()),
            null, null, null
        )

        while (cursor.moveToNext()) {
            items.add(TripItem(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                tripId = cursor.getInt(cursor.getColumnIndexOrThrow("trip_id")),
                name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                isChecked = cursor.getInt(cursor.getColumnIndexOrThrow("is_checked")) == 1
            ))
        }

        cursor.close()
        //db.close()

        return items
    }

    fun deleteTrip(tripId: Int): Boolean {
        val db = dbHelper.writableDatabase

        return try {
            // ON DELETE CASCADE powinno usunąć również wszystkie przedmioty
            val rowsDeleted = db.delete(
                "trips",
                "id = ?",
                arrayOf(tripId.toString())
            )

            rowsDeleted > 0
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas usuwania wyprawy", e)
            false
        } finally {
            //db.close()
        }
    }



    private fun cursorToTrip(cursor: Cursor): Trip {
        return Trip(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
            title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
            date = cursor.getLong(cursor.getColumnIndexOrThrow("date")),
            location = cursor.getString(cursor.getColumnIndexOrThrow("location")),
            forestType = cursor.getString(cursor.getColumnIndexOrThrow("forest_type")),
            notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"))
        )
    }
}