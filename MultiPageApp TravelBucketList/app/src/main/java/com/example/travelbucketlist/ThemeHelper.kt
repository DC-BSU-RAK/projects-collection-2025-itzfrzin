package com.example.travelbucketlist

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.travelbucketlist.R

object ThemeHelper {

    private const val PREFS_NAME = "MyPrefs"
    private const val KEY_THEME = "theme"

    const val LIGHT = "Light"
    const val DARK = "Dark"


    fun applyTheme(activity: androidx.appcompat.app.AppCompatActivity) {
        val sharedPref = activity.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val theme = sharedPref.getString(KEY_THEME, LIGHT) ?: LIGHT

        when (theme) {
            LIGHT -> {
                activity.setTheme(R.style.Theme_TravelBucketList_Light)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            DARK -> {
                activity.setTheme(R.style.Theme_TravelBucketList_Dark)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                activity.setTheme(R.style.Theme_TravelBucketList_Light)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    // Save user preference
    fun saveTheme(context: Context, theme: String) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(KEY_THEME, theme)
            apply()
        }
    }

    // Get current saved theme
    fun getSavedTheme(context: Context): String {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref.getString(KEY_THEME, LIGHT) ?: LIGHT
    }
}
