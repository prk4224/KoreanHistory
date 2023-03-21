package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.result.ApiResult
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getRemoteStudyInfo(dynastyType: String, studyType: String): Flow<ApiResult<List<StudyInfoItem>>>
}