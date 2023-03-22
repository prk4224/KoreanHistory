package com.jaehong.data.local.preference

interface KoreanHistoryPreference {
    fun getGuideState(key: String): Boolean

    fun setGuideState(key: String, value: Boolean)

    fun getRemoteUpdateState(key: String): Boolean

    fun setRemoteUpdateState(key: String, state: Boolean)
}