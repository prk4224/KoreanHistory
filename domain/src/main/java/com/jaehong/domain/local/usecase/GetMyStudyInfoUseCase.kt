package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import javax.inject.Inject

class GetMyStudyInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke() : List<StudyInfoItem>{
        return localRepository.gatMyStudyInfo()
    }

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        localRepository.deleteMyStudyInfo(studyList)
    }

    suspend fun getGuideInfo(key: String): Boolean {
        return localRepository.getGuideInfo(key)
    }

    suspend fun setGuideInfo(key: String) {
        localRepository.setGuideInfo(key)
    }
}