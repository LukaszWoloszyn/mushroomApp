package com.example.mushroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.ClassificationHistory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ClassificationHistoryAdapter(
    private var historyItems: List<ClassificationHistory>,
    private val onDeleteClick: (ClassificationHistory) -> Unit
) : RecyclerView.Adapter<ClassificationHistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyImage: ImageView = view.findViewById(R.id.history_image)
        val historyResult: TextView = view.findViewById(R.id.history_result)
        val historyDate: TextView = view.findViewById(R.id.history_date)
        val deleteButton: ImageButton = view.findViewById(R.id.delete_history_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classification_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun getItemCount() = historyItems.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyItems[position]

        // Load image from path
        try {
            val bitmap = android.graphics.BitmapFactory.decodeFile(item.imagePath)
            holder.historyImage.setImageBitmap(bitmap)
        } catch (e: Exception) {
            holder.historyImage.setImageResource(R.color.light_gray)
        }

        // Set result text
        holder.historyResult.text = item.result

        // Format and set date
        val dateFormat = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
        holder.historyDate.text = dateFormat.format(Date(item.date))

        holder.deleteButton.setOnClickListener {
            onDeleteClick(item)
        }
    }

    fun updateData(newItems: List<ClassificationHistory>) {
        historyItems = newItems
        notifyDataSetChanged()
    }

}
