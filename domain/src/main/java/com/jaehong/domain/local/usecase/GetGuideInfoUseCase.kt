package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import javax.inject.Inject

class GetGuideInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {

    suspend operator fun invoke(key: String) : Boolean{
        return localRepository.getGuideInfo(key)
    }

    suspend fun setGuideInfo(key: String){
        localRepository.setGuideInfo(key)
    }
}