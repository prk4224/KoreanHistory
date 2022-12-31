package com.jaehong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.jaehong.data.local.model.StudyInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ): LocalDataSource {

    override suspend fun getStudyInfo(): StudyInfo {
        val json = context.assets.open("Test.json").reader().readText()
        return Gson().fromJson(json, StudyInfo::class.java)
    }
}