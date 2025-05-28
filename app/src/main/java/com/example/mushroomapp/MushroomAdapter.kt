package com.example.mushroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.Mushroom
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MushroomAdapter(
    private var mushrooms: List<Mushroom>,
    private val onItemClick: (Mushroom) -> Unit
) : RecyclerView.Adapter<MushroomAdapter.MushroomViewHolder>() {

    class MushroomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mushroomImage: ImageView = view.findViewById(R.id.mushroom_image)
        val mushroomName: TextView = view.findViewById(R.id.mushroom_name)
        val mushroomConfidence: TextView = view.findViewById(R.id.mushroom_confidence)
        val mushroomDate: TextView = view.findViewById(R.id.mushroom_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MushroomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mushroom, parent, false)
        return MushroomViewHolder(view)
    }

    override fun getItemCount(): Int = mushrooms.size

    override fun onBindViewHolder(holder: MushroomViewHolder, position: Int) {
        val mushroom = mushrooms[position]

        holder.itemView.setOnClickListener {
            onItemClick(mushrooms[position])
        }

        // Wczytaj obraz z ścieżki
        try {
            val bitmap = android.graphics.BitmapFactory.decodeFile(mushroom.imagePath)
            holder.mushroomImage.setImageBitmap(bitmap)
        } catch (e: Exception) {
            holder.mushroomImage.setImageResource(R.color.light_gray)
        }

        // Ustaw nazwę grzyba
        holder.mushroomName.text = mushroom.mushroomName

        // Ustaw pewność identyfikacji
        holder.mushroomConfidence.text = "Pewność: ${mushroom.confidence}%"

        // Formatuj i ustaw datę
        val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        holder.mushroomDate.text = dateFormat.format(Date(mushroom.date))
    }

    fun updateData(newMushrooms: List<Mushroom>) {
        mushrooms = newMushrooms
        notifyDataSetChanged()
    }
}
