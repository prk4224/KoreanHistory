package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.toDomain
import com.jaehong.domain.local.model.StudyInfo
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): com.jaehong.domain.local.repository.LocalRepository {

    override suspend fun getStudyInfo(): StudyInfo {
        return dataSource.getStudyInfo().toDomain()
    }
}