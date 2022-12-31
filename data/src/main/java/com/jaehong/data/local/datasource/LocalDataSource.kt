package com.jaehong.data.local.datasource

import com.jaehong.data.local.model.StudyInfo

interface LocalDataSource {
    suspend fun getStudyInfo(): StudyInfo
}