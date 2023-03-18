package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMyStudyInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(): Flow<List<StudyInfoItem>> = flow {
        localRepository.getMyStudyInfo().collect {
            emit(it)
        }
    }

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        localRepository.deleteMyStudyInfo(studyList)
    }

    suspend fun getGuideState(key: String): Flow<Boolean> = flow {
        localRepository.getGuideState(key).collect {
            emit(it)
        }
    }

    suspend fun setGuideState(key: String) {
        localRepository.setGuideState(key)
    }
}