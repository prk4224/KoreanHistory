package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import javax.inject.Inject

class GetStudyInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(dynastyType: String): StudyInfo {
        return localRepository.getAllStudyInfo(dynastyType)
    }

    suspend fun getStudyIngo(dynastyType: String, studyType: String): StudyInfo {
        return localRepository.getStudyInfo(dynastyType, studyType)
    }

    suspend fun insertMyStudyInfo(studyInfo: List<StudyInfoItem>) {
        localRepository.insertMyStudyInfo(studyInfo)
    }

    suspend fun getGuideInfo(key: String): Boolean {
        return localRepository.getGuideInfo(key)
    }

    suspend fun setGuideInfo(key: String) {
        localRepository.setGuideInfo(key)
    }
}