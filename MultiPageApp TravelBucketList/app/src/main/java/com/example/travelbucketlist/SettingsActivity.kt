package com.example.travelbucketlist

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonSave: Button
    private lateinit var switchNotifications: Switch
    private lateinit var buttonGoHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Show back button in the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Connect views
        radioGroup = findViewById(R.id.radioGroupTheme)
        buttonSave = findViewById(R.id.buttonSaveSettings)
        switchNotifications = findViewById(R.id.switchNotifications)
        buttonGoHome = findViewById(R.id.buttonGoHome)

        // Load saved theme preference
        val savedTheme = ThemeHelper.getSavedTheme(this)
        if (savedTheme == "Light") {
            radioGroup.check(R.id.radioLight)
        } else {
            radioGroup.check(R.id.radioDark)
        }

        // Load saved notification toggle state
        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val notificationsEnabled = prefs.getBoolean("notifications_enabled", true)
        switchNotifications.isChecked = notificationsEnabled

        // Save Settings button
        buttonSave.setOnClickListener {
            // Save theme selection
            val selectedId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = findViewById<RadioButton>(selectedId)
            val theme = selectedRadioButton.text.toString()
            ThemeHelper.saveTheme(this, theme)

            // Save notification toggle
            val enableNotifications = switchNotifications.isChecked
            prefs.edit().putBoolean("notifications_enabled", enableNotifications).apply()

            Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show()


            recreate()
        }

        // Go to Home button
        buttonGoHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Handle action bar back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
