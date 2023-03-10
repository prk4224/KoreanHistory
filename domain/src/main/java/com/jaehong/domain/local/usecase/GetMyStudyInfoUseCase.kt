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
        localRepository.gatMyStudyInfo().collect {
            emit(it)
        }
    }

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        localRepository.deleteMyStudyInfo(studyList)
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