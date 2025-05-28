package com.example.mushroomapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.model.Mushroom

class MushroomRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private val TAG = "MushroomRepository"

    fun saveMushroom(mushroom: Mushroom): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_id", mushroom.userId)
            put("image_path", mushroom.imagePath)
            put("mushroom_name", mushroom.mushroomName)
            put("confidence", mushroom.confidence)
            put("date", mushroom.date)
        }

        return try {
            db.insert("mushrooms", null, values)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas zapisywania grzyba", e)
            -1
        } finally {
            db.close()
        }
    }

    fun getMushrooms(userId: Int): List<Mushroom> {
        val mushrooms = mutableListOf<Mushroom>()
        val db = dbHelper.readableDatabase

        try {
            val cursor = db.query(
                "mushrooms",
                null,
                "user_id = ?",
                arrayOf(userId.toString()),
                null, null,
                "date DESC"
            )

            while (cursor.moveToNext()) {
                mushrooms.add(
                    Mushroom(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                        imagePath = cursor.getString(cursor.getColumnIndexOrThrow("image_path")),
                        mushroomName = cursor.getString(cursor.getColumnIndexOrThrow("mushroom_name")),
                        confidence = cursor.getInt(cursor.getColumnIndexOrThrow("confidence")),
                        date = cursor.getLong(cursor.getColumnIndexOrThrow("date"))
                    )
                )
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas pobierania grzybów", e)
        } finally {
            db.close()
        }

        return mushrooms
    }

    fun getMushroomById(id: Int): Mushroom? {
        val db = dbHelper.readableDatabase
        var mushroom: Mushroom? = null

        try {
            val cursor = db.query(
                "mushrooms",
                null,
                "id = ?",
                arrayOf(id.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                mushroom = Mushroom(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                    imagePath = cursor.getString(cursor.getColumnIndexOrThrow("image_path")),
                    mushroomName = cursor.getString(cursor.getColumnIndexOrThrow("mushroom_name")),
                    confidence = cursor.getInt(cursor.getColumnIndexOrThrow("confidence")),
                    date = cursor.getLong(cursor.getColumnIndexOrThrow("date"))
                )
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e("MushroomRepository", "Błąd podczas pobierania grzyba", e)
        }

        return mushroom
    }

    fun deleteMushroom(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val result = db.delete("mushrooms", "id = ?", arrayOf(id.toString()))
            result > 0
        } catch (e: Exception) {
            Log.e("MushroomRepository", "Błąd podczas usuwania grzyba", e)
            false
        }
    }

}
