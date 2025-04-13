package com.example.mushroomapp

import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mushroomapp.data.model.Trip
import com.example.mushroomapp.data.model.TripItem
import com.example.mushroomapp.data.repository.TripRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddEditTripActivity : AppCompatActivity() {
    private lateinit var tripRepository: TripRepository
    private lateinit var sessionManager: SessionManager
    private lateinit var itemsAdapter: TripItemsAdapter

    private val items = mutableListOf<TripItem>()
    private var selectedDate: Long = 0
    private var editingTripId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_trip)

        // Inicjalizacja
        tripRepository = TripRepository(this)
        sessionManager = SessionManager(this)

        // Konfiguracja spinner typu lasu
        setupForestTypeSpinner()

        // Konfiguracja wyboru daty
        setupDatePicker()

        // Konfiguracja listy przedmiotów
        setupItemsList()

        // Obsługa przycisku dodawania przedmiotu
        findViewById<Button>(R.id.add_item_button).setOnClickListener {
            addItemToList()
        }

        // Obsługa przycisku zapisz wyprawę
        findViewById<Button>(R.id.save_trip_button).setOnClickListener {
            saveTrip()
        }

        // Obsługa przycisku sprawdź pogodę
        findViewById<Button>(R.id.check_weather_button).setOnClickListener {
            checkWeatherForecast()
        }

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Obsługa przycisku powrotu
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupForestTypeSpinner() {
        val spinner = findViewById<Spinner>(R.id.forest_type_spinner)
        val forestTypes = arrayOf("Las mieszany", "Las iglasty", "Las liściasty", "Inne")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, forestTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun setupDatePicker() {
        val dateInput = findViewById<EditText>(R.id.trip_date_input)
        dateInput.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePickerDialog = DatePickerDialog(
                this,
                R.style.DatePickerDialogTheme,  // Dodaj bezpośrednie odniesienie do stylu
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    selectedDate = calendar.timeInMillis

                    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    dateInput.setText(dateFormat.format(calendar.time))
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            // Możesz również dostosować przyciski bezpośrednio
            datePickerDialog.setOnShowListener {
                val positiveButton = datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                val negativeButton = datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)

                positiveButton.setTextColor(Color.parseColor("black"))
                negativeButton.setTextColor(Color.parseColor("black"))
            }

            datePickerDialog.show()

        }
    }

    private fun setupItemsList() {
        val recyclerView = findViewById<RecyclerView>(R.id.items_recycler_view)
        itemsAdapter = TripItemsAdapter(items)
        recyclerView.adapter = itemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Dodanie domyślnych przedmiotów
        val defaultItems = listOf("Koszyk", "Nóż do grzybów", "Woda", "Mapa")
        defaultItems.forEach {
            items.add(TripItem(tripId = 0, name = it))
        }
        itemsAdapter.notifyDataSetChanged()
    }

    private fun addItemToList() {
        val itemInput = findViewById<EditText>(R.id.new_item_input)
        val itemName = itemInput.text.toString().trim()

        if (itemName.isNotEmpty()) {
            items.add(TripItem(tripId = 0, name = itemName))
            itemsAdapter.notifyItemInserted(items.size - 1)
            itemInput.text.clear()
        }
    }

    private fun saveTrip() {
        // Walidacja wprowadzonych danych
        val titleInput = findViewById<EditText>(R.id.trip_title_input)
        val locationInput = findViewById<EditText>(R.id.trip_location_input)
        val forestTypeSpinner = findViewById<Spinner>(R.id.forest_type_spinner)

        val title = titleInput.text.toString().trim()
        val location = locationInput.text.toString().trim()
        val forestType = forestTypeSpinner.selectedItem.toString()

        if (title.isEmpty() || location.isEmpty() || selectedDate == 0L) {
            Toast.makeText(this, "Wypełnij wszystkie wymagane pola", Toast.LENGTH_SHORT).show()
            return
        }

        // Zapisanie wyprawy
        val userId = sessionManager.getUserId()
        val trip = Trip(
            id = if (editingTripId != -1) editingTripId else 0,
            userId = userId,
            title = title,
            date = selectedDate,
            location = location,
            forestType = forestType
        )

        val tripId = if (editingTripId != -1) {
            tripRepository.updateTrip(trip)
            editingTripId
        } else {
            tripRepository.createTrip(trip).toInt()
        }

        if (tripId > 0) {
            // Zapisanie przedmiotów
            if (editingTripId != -1) {
                // Usunięcie starych przedmiotów przy edycji
                tripRepository.deleteAllItemsForTrip(tripId)
            }

            for (item in items) {
                tripRepository.addTripItem(item.copy(tripId = tripId))
            }

            Toast.makeText(this, "Wyprawa została zapisana", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "Wystąpił błąd podczas zapisywania wyprawy", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkWeatherForecast() {
        // Implementacja sprawdzania prognozy pogody
        // To wymaga integracji z API pogodowym
        Toast.makeText(this, "Funkcja w przygotowaniu", Toast.LENGTH_SHORT).show()
    }
}
