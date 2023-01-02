package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.repository.LocalRepository
import javax.inject.Inject

class StudyPageUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(): StudyInfo {
        return localRepository.getStudyInfo()
    }
}