package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getLocalStudyInfo(dynastyType: String, studyType: String): Flow<List<StudyInfoItem>>

    suspend fun insertLocalStudyIndo(studyList: List<StudyInfoItem>,dynastyType: String, studyType: String)

    suspend fun getMyStudyInfo(): Flow<List<StudyInfoItem>>

    suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun getRemoteUpdateState(dynastyType: String, studyType: String): Flow<Boolean>

    suspend fun setRemoteUpdateState(dynastyType: String, studyType: String, state: Boolean)

    suspend fun getGuideState(key: String): Flow<Boolean>

    suspend fun setGuideState(key: String)

    suspend fun observeConnectivityAsFlow(): Flow<Boolean>
}