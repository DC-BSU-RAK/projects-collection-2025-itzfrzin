package com.example.travelbucketlist

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class BucketListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply saved theme
        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bucket_list)

        // Show back button on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Set up ListView
        val listView = findViewById<ListView>(R.id.listViewBucket)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.bucketList)
        listView.adapter = adapter

        // Set up "Go to Home" button
        val buttonGoHome = findViewById<Button>(R.id.buttonGoHomeFromBucket)
        buttonGoHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // Optionally clear activity stack to avoid multiple instances
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
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
