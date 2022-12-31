package com.jaehong.data.datasource

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class StudyPageDataSource @Inject constructor(
    @ApplicationContext private val context: Context
    ) {

    fun getStudyInfo(): com.jaehong.data.model.StudyInfo {
        val json = context.assets.open("Test.json").reader().readText()
        return Gson().fromJson(json, com.jaehong.data.model.StudyInfo::class.java)
    }
}