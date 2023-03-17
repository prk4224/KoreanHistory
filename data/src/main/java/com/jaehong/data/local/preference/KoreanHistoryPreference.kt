package com.jaehong.data.local.preference

import android.content.SharedPreferences
import com.jaehong.data.util.Constants.STRING_EMPTY

class KoreanHistoryPreference constructor(private val prefs: SharedPreferences) {
    fun getString(key: String): Boolean {
        return prefs.getString(key,STRING_EMPTY) == STRING_EMPTY
    }

    fun setString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun getState(key: String): Boolean {
        return prefs.getBoolean(key,false)
    }

    fun setState(key: String, state: Boolean) {
        prefs.edit().putBoolean(key,state).apply()
    }
}