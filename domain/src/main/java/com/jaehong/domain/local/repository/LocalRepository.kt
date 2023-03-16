package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getLocalStudyInfo(dynastyType: String, studyType: String): Flow<List<StudyInfoItem>>

    suspend fun insertLocalStudyIndo(studyList: List<StudyInfoItem>,dynastyType: String, studyType: String)

    suspend fun getMyStudyInfo(): Flow<List<StudyInfoItem>>

    suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun getGuideInfo(key: String): Flow<Boolean>

    suspend fun setGuideInfo(key: String)
}