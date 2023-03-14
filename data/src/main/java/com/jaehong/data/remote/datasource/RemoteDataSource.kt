package com.jaehong.data.remote.datasource

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.remote.model.StudyEntity
import com.jaehong.data.util.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllStudyInfo(dynastyType: DynastyDetailType): Flow<StudyEntity>

    suspend fun getStudyInfo(dynastyType: DynastyDetailType): Flow<StudyEntity>

}