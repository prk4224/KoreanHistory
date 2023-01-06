package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import javax.inject.Inject

class GetStudyInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(dynastyType: String, studyType: String): StudyInfo {
        return localRepository.getAllStudyInfo(dynastyType,studyType)
    }

    suspend fun getStudyIngo(dynastyType: String, studyType: String): StudyInfo {
        return localRepository.gatStudyInfo(dynastyType,studyType)
    }

    suspend fun addMyStudyInfo(studyInfo: List<StudyInfoItem>){
        localRepository.addMyStudyInfo(studyInfo)
    }
}