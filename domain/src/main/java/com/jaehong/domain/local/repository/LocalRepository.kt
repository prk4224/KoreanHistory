package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.result.DbResult
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getLocalStudyInfo(dynastyType: String, studyType: String): Flow<DbResult<List<StudyInfoItem>>>

    suspend fun insertLocalStudyIndo(
        studyList: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String,
    ): Flow<DbResult<Boolean>>

    suspend fun getMyStudyInfo(): Flow<DbResult<List<StudyInfoItem>>>

    suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>): Flow<DbResult<Boolean>>

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>): Flow<DbResult<Boolean>>

    suspend fun getRemoteUpdateState(dynastyType: String, studyType: String): Flow<Boolean>

    suspend fun setRemoteUpdateState(dynastyType: String, studyType: String, state: Boolean)

    suspend fun getGuideState(key: String): Flow<Boolean>

    suspend fun setGuideState(key: String)

    suspend fun observeConnectivityAsFlow(): Flow<Boolean>
}