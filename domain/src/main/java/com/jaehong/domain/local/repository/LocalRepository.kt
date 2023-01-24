package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getAllStudyInfo(dynastyType: String): Flow<StudyInfo>

    suspend fun getStudyInfo(dynastyType: String, studyType: String): Flow<StudyInfo>

    suspend fun gatMyStudyInfo(): Flow<List<StudyInfoItem>>

    suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun getGuideInfo(key: String): Flow<Boolean>

    suspend fun setGuideInfo(key: String)
}