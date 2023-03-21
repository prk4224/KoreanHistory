package com.jaehong.data.local.datasource

import android.content.Context
import android.net.ConnectivityManager
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.result.DbResult
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun getLocalStudyInfo(studyType: String): Flow<DbResult<List<StudyInfoEntity>>>

    suspend fun insertLocalStudyIndo(studyList: List<StudyInfoEntity>): Flow<DbResult<Boolean>>

    suspend fun getMyStudyInfo(): Flow<DbResult<List<MyStudyEntity>>>

    suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>): Flow<DbResult<Boolean>>

    suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>): Flow<DbResult<Boolean>>

    suspend fun getRemoteUpdateState(dynastyType: DynastyDetailType, studyType: String): Flow<Boolean>

    suspend fun setRemoteUpdateState(dynastyType: DynastyDetailType, studyType: String, state: Boolean)

    suspend fun getGuideState(key: String): Flow<Boolean>

    suspend fun setGuideState(key: String)

    suspend fun observeConnectivityAsFlow(): Flow<Boolean>
}