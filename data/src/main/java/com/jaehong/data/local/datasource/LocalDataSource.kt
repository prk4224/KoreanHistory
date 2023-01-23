package com.jaehong.data.local.datasource

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getAllStudyInfo(dynastyType: String): Flow<StudyEntity>

    suspend fun getStudyInfo(dynastyType: String, studyType: String): StudyEntity

    suspend fun gatMyStudyInfo(): List<MyStudyEntity>

    suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun getGuideInfo(key: String): Boolean

    suspend fun setGuideInfo(key: String)

}