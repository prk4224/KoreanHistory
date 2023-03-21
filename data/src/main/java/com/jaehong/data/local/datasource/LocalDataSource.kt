package com.jaehong.data.local.datasource

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getLocalStudyInfo(studyType: String): Flow<List<StudyInfoEntity>>

    suspend fun insertLocalStudyIndo(studyList: List<StudyInfoEntity>)

    suspend fun getMyStudyInfo(): Flow<List<MyStudyEntity>>

    suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun getRemoteUpdateState(dynastyType: DynastyDetailType, studyType: String): Flow<Boolean>

    suspend fun setRemoteUpdateState(dynastyType: DynastyDetailType, studyType: String, state: Boolean)

    suspend fun getGuideState(key: String): Flow<Boolean>

    suspend fun setGuideState(key: String)

    suspend fun observeConnectivityAsFlow(): Flow<Boolean>
}