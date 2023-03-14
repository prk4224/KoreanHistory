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
    suspend operator fun invoke(
        dynastyType: String
    ): Flow<StudyInfo> = flow {
        remoteRepository.getAllStudyInfo(dynastyType).collect {
            emit(it)
        }
    }

    suspend fun getStudyIngo(
        dynastyType: String
    ): Flow<StudyInfo> = flow {
        remoteRepository.getStudyInfo(dynastyType).collect {
            emit(it)
        }
    }

    suspend fun insertMyStudyInfo(studyInfo: List<StudyInfoItem>) {
        localRepository.insertMyStudyInfo(studyInfo)
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