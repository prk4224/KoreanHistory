package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getRemoteStudyInfo(dynastyType: String, studyType: String): Flow<StudyInfo>
}