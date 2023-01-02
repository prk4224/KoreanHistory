package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo

interface LocalRepository {
    suspend fun getStudyInfo(): StudyInfo
}