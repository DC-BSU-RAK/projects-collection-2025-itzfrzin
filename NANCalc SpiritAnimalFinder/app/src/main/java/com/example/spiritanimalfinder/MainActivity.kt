package com.example.spiritanimalfinder

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var foodSpinner: Spinner
    lateinit var weatherSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var resultText: TextView
    lateinit var calculateButton: Button
    lateinit var infoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI ID references
        foodSpinner = findViewById(R.id.foodSpinner)
        weatherSpinner = findViewById(R.id.weatherSpinner)
        timeSpinner = findViewById(R.id.timeSpinner)
        resultText = findViewById(R.id.resultText)
        calculateButton = findViewById(R.id.calculateButton)
        infoButton = findViewById(R.id.infoButton)

        // Intent logic
        val spiritAnimal = intent.getStringExtra("spirit_animal")
        if (spiritAnimal != null) {
            resultText.text = "Your spirit animal is: $spiritAnimal"
        } else {
            Log.d("SpiritAnimal", "No intent extra passed")
        }

        // Spinner setup
        ArrayAdapter.createFromResource(
            this, R.array.food_options, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            foodSpinner.adapter = it
        }

        ArrayAdapter.createFromResource(
            this, R.array.weather_options, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            weatherSpinner.adapter = it
        }

        ArrayAdapter.createFromResource(
            this, R.array.time_options, android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            timeSpinner.adapter = it
        }

        // Button click for result
        calculateButton.setOnClickListener {
            val food = foodSpinner.selectedItem.toString()
            val weather = weatherSpinner.selectedItem.toString()
            val time = timeSpinner.selectedItem.toString()
            resultText.text = getSpiritAnimal(food, weather, time)
        }

        // Button click for Info dialog with custom style
        infoButton.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            builder.setTitle("About This App")
                .setMessage("Pick your favorites and discover your spirit animal!")
                .setPositiveButton("OK", null)
                .show()
        }
    }

    // Logic to find spirit animal
    private fun getSpiritAnimal(food: String, weather: String, time: String): String {
        return when {
            food == "Pizza" && weather == "Sunny" && time == "Evening" -> "🦁 You are a Lion! Bold and confident."
            food == "Sushi" && weather == "Rainy" && time == "Night" -> "🦉 You are an Owl! Calm and wise."
            food == "Burgers" && weather == "Snowy" && time == "Morning" -> "🐻 You are a Bear! Strong and steady."
            else -> "🦊 You are a Fox! Clever, curious, and adaptable."
        }
    }
}
