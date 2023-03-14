package com.jaehong.data.remote.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.checkedType
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): RemoteRepository {

    override suspend fun getAllStudyInfo(
        dynastyType: String
    ): Flow<StudyInfo> = flow {
        dataSource.getAllStudyInfo(dynastyType.checkedType()).collect {
            emit(it.dataToDomain())
        }
    }

    override suspend fun getStudyInfo(
        dynastyType: String,
    ): Flow<StudyInfo> = flow {
        dataSource.getStudyInfo(dynastyType.checkedType()).collect {
            emit(it.dataToDomain())
        }
    }
}