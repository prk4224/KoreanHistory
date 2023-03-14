package com.jaehong.data.local.datasource

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.util.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun gatMyStudyInfo(): Flow<List<MyStudyEntity>>

    suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun getGuideInfo(key: String): Flow<Boolean>

    suspend fun setGuideInfo(key: String)

}