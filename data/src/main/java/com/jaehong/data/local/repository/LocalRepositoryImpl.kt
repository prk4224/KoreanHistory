package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.changeDataToRepository
import com.jaehong.domain.local.model.UiStudyInfo
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): com.jaehong.domain.local.repository.LocalRepository {

    override suspend fun getStudyInfo(): UiStudyInfo {
        return dataSource.getStudyInfo().changeDataToRepository()
    }
}