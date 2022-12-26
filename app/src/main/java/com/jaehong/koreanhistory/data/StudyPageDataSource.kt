package com.jaehong.koreanhistory.data

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StudyPageDataSource @Inject constructor(
    @ApplicationContext private val context: Context
    ) {

    fun getStudyInfo(): StudyInfo {
        val json = context.assets.open("Test.json").reader().readText()
        return Gson().fromJson(json,StudyInfo::class.java)
    }
}