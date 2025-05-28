package com.example.mushroomapp.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ImageUtils {
    fun saveImageToInternalStorage(context: Context, bitmap: Bitmap): String {
        val fileName = "mushroom_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        try {
            FileOutputStream(file).use { stream ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream)
            }
            return file.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            return ""
        }
    }
}
