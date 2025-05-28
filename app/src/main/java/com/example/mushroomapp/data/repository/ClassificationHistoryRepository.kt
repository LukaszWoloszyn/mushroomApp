package com.example.mushroomapp.data.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.mushroomapp.data.local.DatabaseHelper
import com.example.mushroomapp.data.model.ClassificationHistory

class ClassificationHistoryRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private val TAG = "ClassificationHistoryRepo"
    private val MAX_HISTORY_ITEMS = 20

    fun saveClassification(history: ClassificationHistory): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_id", history.userId)
            put("image_path", history.imagePath)
            put("result", history.result)
            put("date", history.date)
        }

        val id = try {
            db.insert("classification_history", null, values)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas zapisywania klasyfikacji", e)
            -1
        }

        if (id != -1L) {
            limitHistoryItems(db, history.userId)
        }

        db.close()
        return id
    }

    private fun limitHistoryItems(db: SQLiteDatabase, userId: Int) {
        try {
            db.execSQL("""
                DELETE FROM classification_history 
                WHERE user_id = $userId AND id NOT IN (
                    SELECT id FROM classification_history 
                    WHERE user_id = $userId 
                    ORDER BY date DESC LIMIT $MAX_HISTORY_ITEMS
                )
            """)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas ograniczania historii", e)
        }
    }

    fun getHistory(userId: Int): List<ClassificationHistory> {
        val history = mutableListOf<ClassificationHistory>()
        val db = dbHelper.readableDatabase

        try {
            val cursor = db.query(
                "classification_history",
                null,
                "user_id = ?",
                arrayOf(userId.toString()),
                null, null,
                "date DESC"
            )

            while (cursor.moveToNext()) {
                history.add(
                    ClassificationHistory(
                        id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id")),
                        imagePath = cursor.getString(cursor.getColumnIndexOrThrow("image_path")),
                        result = cursor.getString(cursor.getColumnIndexOrThrow("result")),
                        date = cursor.getLong(cursor.getColumnIndexOrThrow("date"))
                    )
                )
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas pobierania historii", e)
        } finally {
            db.close()
        }

        return history
    }

    fun deleteHistoryItem(id: Int): Boolean {
        val db = dbHelper.writableDatabase
        return try {
            val rowsDeleted = db.delete(
                "classification_history",
                "id = ?",
                arrayOf(id.toString())
            )
            rowsDeleted > 0
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas usuwania elementu historii", e)
            false
        } finally {
            db.close()
        }
    }

}
