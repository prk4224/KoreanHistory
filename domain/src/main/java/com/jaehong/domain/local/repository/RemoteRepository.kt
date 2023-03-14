package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun getAllStudyInfo(dynastyType: String): Flow<StudyInfo>

    suspend fun getStudyInfo(dynastyType: String): Flow<StudyInfo>
}