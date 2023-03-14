package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.dataBaseToDomain
import com.jaehong.data.mapper.Mapper.domainToDataBase
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): LocalRepository {

    override suspend fun gatMyStudyInfo(): Flow<List<StudyInfoItem>> = flow {
        dataSource.gatMyStudyInfo().collect {
            emit(it.dataBaseToDomain().item)
        }
    }

    override suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.insertMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.deleteMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun getGuideInfo(key: String): Flow<Boolean> = flow {
        dataSource.getGuideInfo(key).collect {
            emit(it)
        }
    }

    override suspend fun setGuideInfo(key: String) {
        dataSource.setGuideInfo(key)
    }
}