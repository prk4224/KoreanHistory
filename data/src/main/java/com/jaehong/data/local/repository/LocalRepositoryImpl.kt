package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.dataBaseToDomain
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.data.mapper.Mapper.domainToDataBase
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): com.jaehong.domain.local.repository.LocalRepository {

    override suspend fun getAllStudyInfo(
        dynastyType: String
    ): Flow<StudyInfo> = flow {
        dataSource.getAllStudyInfo(dynastyType).collect {
            emit(it.dataToDomain())
        }
    }

    override suspend fun getStudyInfo(
        dynastyType: String,
        studyType: String
    ): Flow<StudyInfo> = flow {
        dataSource.getStudyInfo(dynastyType,studyType).collect {
            emit(it. dataToDomain())
        }
    }

    override suspend fun gatMyStudyInfo(): Flow<List<StudyInfoItem>> = flow {
        dataSource.gatMyStudyInfo().collect {
            emit(it.dataBaseToDomain())
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