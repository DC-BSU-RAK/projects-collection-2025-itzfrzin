package com.example.travelbucketlist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {

        val bucketList = mutableListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editTextDestination = findViewById<EditText>(R.id.editTextDestination)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonShowList = findViewById<Button>(R.id.buttonShowList)
        val buttonSettings = findViewById<Button>(R.id.buttonSettings)
        val buttonInfo = findViewById<Button>(R.id.buttonInfo)

        // Add destination to list
        buttonAdd.setOnClickListener {
            val destination = editTextDestination.text.toString().trim()
            if (destination.isNotEmpty()) {
                bucketList.add(destination)
                Toast.makeText(this, "Added: $destination", Toast.LENGTH_SHORT).show()
                editTextDestination.text.clear()
            } else {
                Toast.makeText(this, "Please enter a destination", Toast.LENGTH_SHORT).show()
            }
        }

        // Open Bucket List screen
        buttonShowList.setOnClickListener {
            val intent = Intent(this, BucketListActivity::class.java)
            startActivity(intent)
        }

        // Open Settings screen
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        // Show Info popup
        buttonInfo.setOnClickListener {
            showInstructions()
        }
    }

    // Instructions popup
    private fun showInstructions() {
        val dialog = AlertDialog.Builder(this, R.style.CustomDialogTheme)
            .setTitle("App Instructions")
            .setMessage(
                "Welcome to Travel Bucket List!\n\n" +
                        "• Add destinations on the main screen.\n" +
                        "• View your list in Bucket List.\n" +
                        "• Save your preferences in Settings."
            )
            .setPositiveButton("Got it") { dialog, _ -> dialog.dismiss() }
            .create()
        dialog.show()
    }
}
