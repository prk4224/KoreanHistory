package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.result.DbResult
import com.jaehong.domain.local.model.result.ApiResult
import com.jaehong.domain.local.repository.LocalRepository
import com.jaehong.domain.local.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStudyInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {

    suspend fun getRemoteStudyInfo(
        dynastyType: String,
        studyType: String,
    ): Flow<ApiResult<List<StudyInfoItem>>> = flow {
        remoteRepository.getRemoteStudyInfo(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun getLocalStudyInfo(
        dynastyType: String,
        studyType: String,
    ): Flow<DbResult<List<StudyInfoItem>>> = flow {
        localRepository.getLocalStudyInfo(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun insertLocalStudyInfo(
        studyInfo: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String,
    ): Flow<DbResult<Boolean>> = flow {
        localRepository.insertLocalStudyIndo(studyInfo,dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun insertMyStudyInfo(
        studyInfo: List<StudyInfoItem>
    ): Flow<DbResult<Boolean>> = flow {
        localRepository.insertMyStudyInfo(studyInfo).collect {
            emit(it)
        }
    }

    suspend fun getRemoteUpdateState(
        dynastyType: String,
        studyType: String,
    ): Flow<Boolean> = flow {
        localRepository.getRemoteUpdateState(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun setRemoteUpdateState(
        dynastyType: String,
        studyType: String,
        state: Boolean,
    ) {
        localRepository.setRemoteUpdateState(dynastyType,studyType,state)
    }

    suspend fun getGuideState(key: String): Flow<Boolean> = flow {
        localRepository.getGuideState(key).collect {
            emit(it)
        }
    }

    suspend fun setGuideState(key: String) {
        localRepository.setGuideState(key)
    }

    suspend fun observeConnectivityAsFlow(): Flow<Boolean> = flow {
        localRepository.observeConnectivityAsFlow().collect {
            emit(it)
        }
    }
}