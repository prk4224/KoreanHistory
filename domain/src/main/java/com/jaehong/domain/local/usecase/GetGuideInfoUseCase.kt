package com.jaehong.domain.local.usecase

import com.jaehong.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGuideInfoUseCase @Inject constructor(
    private val localRepository: LocalRepository
) {
    suspend operator fun invoke(key: String) : Flow<Boolean> = flow {
        localRepository.getGuideState(key).collect {
            emit(it)
        }
    }

    suspend fun setGuideState(key: String){
        localRepository.setGuideState(key)
    }
}