package com.example.mushroomapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.repository.MushroomRepository

class MushroomFindingsActivity : AppCompatActivity() {

    private lateinit var mushroomRepository: MushroomRepository
    private lateinit var sessionManager: SessionManager
    private lateinit var adapter: MushroomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mushroom_findings)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        mushroomRepository = MushroomRepository(this)
        sessionManager = SessionManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.findings_recycler_view)
        adapter = MushroomAdapter(emptyList()) { mushroom ->
            val intent = Intent(this, MushroomDetailsActivity::class.java)
            intent.putExtra("MUSHROOM_ID", mushroom.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        loadMushroomFindings()
    }

    override fun onResume() {
        super.onResume()
        loadMushroomFindings()
    }

    private fun loadMushroomFindings() {
        val userId = sessionManager.getUserId()
        val mushrooms = mushroomRepository.getMushrooms(userId)

        adapter.updateData(mushrooms)

        findViewById<View>(R.id.empty_view).visibility =
            if (mushrooms.isEmpty()) View.VISIBLE else View.GONE
    }
}
