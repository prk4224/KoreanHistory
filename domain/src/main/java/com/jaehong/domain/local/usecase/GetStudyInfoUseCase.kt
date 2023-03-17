package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
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
    ): Flow<StudyInfo> = flow {
        remoteRepository.getRemoteStudyInfo(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun getLocalStudyInfo(
        dynastyType: String,
        studyType: String,
    ): Flow<List<StudyInfoItem>> = flow {
        localRepository.getLocalStudyInfo(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun insertLocalStudyInfo(
        studyInfo: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String,
    ) {
        localRepository.insertLocalStudyIndo(studyInfo,dynastyType,studyType)
    }

    suspend fun insertMyStudyInfo(studyInfo: List<StudyInfoItem>) {
        localRepository.insertMyStudyInfo(studyInfo)
    }

    suspend fun getRemoteState(
        dynastyType: String,
        studyType: String,
    ): Flow<Boolean> = flow {
        localRepository.getRemoteState(dynastyType,studyType).collect {
            emit(it)
        }
    }

    suspend fun setRemoteState(
        dynastyType: String,
        studyType: String,
        state: Boolean,
    ) {
        localRepository.setRemoteState(dynastyType,studyType,state)
    }

    suspend fun getGuideInfo(key: String): Flow<Boolean> = flow {
        localRepository.getGuideInfo(key).collect {
            emit(it)
        }
    }

    suspend fun setGuideInfo(key: String) {
        localRepository.setGuideInfo(key)
    }
}