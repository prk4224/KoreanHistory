package com.jaehong.data.local.datasource

import com.jaehong.data.local.model.StudyEntity

interface LocalDataSource {
    suspend fun getStudyInfo(): StudyEntity
}