package com.jaehong.data.local.preference

import android.content.SharedPreferences

class KoreanHistoryPreferenceImpl constructor(private val prefs: SharedPreferences): KoreanHistoryPreference {
    override fun getGuideState(key: String): Boolean {
        return prefs.getBoolean(key,true)
    }

    override fun setGuideState(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    override fun getRemoteUpdateState(key: String): Boolean {
        return prefs.getBoolean(key,false)
    }

    override fun setRemoteUpdateState(key: String, state: Boolean) {
        prefs.edit().putBoolean(key,state).apply()
    }
}