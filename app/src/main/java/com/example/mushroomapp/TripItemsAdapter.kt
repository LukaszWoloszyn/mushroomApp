package com.example.mushroomapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.TripItem

class TripItemsAdapter(
    private val items: MutableList<TripItem>
) : RecyclerView.Adapter<TripItemsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemCheckbox: CheckBox = view.findViewById(R.id.item_checkbox)
        val itemNameTextView: TextView = view.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trip_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]

        holder.itemNameTextView.text = item.name
        holder.itemCheckbox.isChecked = item.isChecked

        // Obsługa zmian checkboxa
        holder.itemCheckbox.setOnCheckedChangeListener { _, isChecked ->
            items[position] = items[position].copy(isChecked = isChecked)
        }
    }

    override fun getItemCount() = items.size
}
