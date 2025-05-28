package com.example.mushroomapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.ClassificationHistory
import com.example.mushroomapp.data.repository.ClassificationHistoryRepository

class ClassificationHistoryActivity : AppCompatActivity() {

    private lateinit var historyRepository: ClassificationHistoryRepository
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: ClassificationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification_history)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        historyRepository = ClassificationHistoryRepository(this)
        sessionManager = SessionManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.history_recycler_view)
        adapter = ClassificationHistoryAdapter(emptyList()) { historyItem ->
            confirmDelete(historyItem)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadClassificationHistory()
    }

    private fun loadClassificationHistory() {
        val userId = sessionManager.getUserId()
        val history = historyRepository.getHistory(userId)

        adapter.updateData(history)

        findViewById<View>(R.id.empty_view).visibility =
            if (history.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun confirmDelete(historyItem: ClassificationHistory) {
        AlertDialog.Builder(this, R.style.AlertDialogStyle)
            .setTitle("Usuń z historii")
            .setMessage("Czy na pewno chcesz usunąć to zdjęcie z historii?")
            .setPositiveButton("Usuń") { _, _ ->
                deleteHistoryItem(historyItem.id)
            }
            .setNegativeButton("Anuluj", null)
            .show()
    }

    private fun deleteHistoryItem(id: Int) {
        if (historyRepository.deleteHistoryItem(id)) {
            Toast.makeText(this, "Usunięto z historii", Toast.LENGTH_SHORT).show()
            loadClassificationHistory() // Odświeżenie listy
        } else {
            Toast.makeText(this, "Błąd podczas usuwania", Toast.LENGTH_SHORT).show()
        }
    }
}
