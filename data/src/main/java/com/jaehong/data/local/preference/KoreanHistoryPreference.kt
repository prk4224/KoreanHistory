package com.jaehong.data.local.preference

import android.content.SharedPreferences
import com.jaehong.data.util.Constants.STRING_EMPTY

interface KoreanHistoryPreference {
    fun getGuideState(key: String): Boolean

    fun setGuideState(key: String, value: Boolean)

    fun getRemoteUpdateState(key: String): Boolean

    fun setRemoteUpdateState(key: String, state: Boolean)
}