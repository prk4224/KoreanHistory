package com.jaehong.data.local

import android.content.SharedPreferences
import com.jaehong.data.util.Constants.STRING_EMPTY

class GuidePreference constructor(private val prefs: SharedPreferences) {
    fun getString(key: String): Boolean {
        return prefs.getString(key,STRING_EMPTY) == STRING_EMPTY
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}