package com.jaehong.data.local.preference

import android.content.SharedPreferences
import com.jaehong.data.util.Constants.STRING_EMPTY

class KoreanHistoryPreference constructor(private val prefs: SharedPreferences) {
    fun getGuideState(key: String): Boolean {
        return prefs.getBoolean(key,true)
    }

    fun setGuideState(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun getRemoteUpdateState(key: String): Boolean {
        return prefs.getBoolean(key,false)
    }

    fun setRemoteUpdateState(key: String, state: Boolean) {
        prefs.edit().putBoolean(key,state).apply()
    }
}