package com.jaehong.data.remote.datasource

import com.jaehong.data.remote.model.StudyEntity
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRemoteStudyInfo(dynastyType: DynastyDetailType, studyType: String): Flow<StudyEntity>
}