package com.jaehong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.jaehong.data.local.model.StudyEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
    ): LocalDataSource {

    override suspend fun getStudyInfo(): StudyEntity {
        val json = context.assets.open("Test.json").reader().readText()
        return Gson().fromJson(json, StudyEntity::class.java)
    }
}