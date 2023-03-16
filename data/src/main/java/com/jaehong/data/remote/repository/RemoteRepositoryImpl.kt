package com.jaehong.data.remote.repository

import com.jaehong.data.mapper.Mapper.checkedType
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.data.remote.datasource.RemoteDataSource
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun getRemoteStudyInfo(
        dynastyType: String,
        studyType: String,
    ): Flow<StudyInfo> = flow {
        dataSource.getRemoteStudyInfo(dynastyType.checkedType(), studyType).collect {
            emit(it.dataToDomain())
        }
    }
}