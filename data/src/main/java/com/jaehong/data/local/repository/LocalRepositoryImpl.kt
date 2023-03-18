package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.checkedType
import com.jaehong.data.mapper.Mapper.dataBaseToDomain
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.data.mapper.Mapper.domainToData
import com.jaehong.data.mapper.Mapper.domainToDataBase
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : LocalRepository {
    override suspend fun getLocalStudyInfo(
        dynastyType: String,
        studyType: String
    ): Flow<List<StudyInfoItem>> = flow {

        dataSource.getLocalStudyInfo(
            dynastyType.checkedType().value(studyType)
        ).collect {
            emit(it.dataToDomain())
        }
    }

    override suspend fun insertLocalStudyIndo(
        studyList: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String
    ) {
        dataSource
            .insertLocalStudyIndo(
                studyList.domainToData(
                    dynastyType.checkedType().value(studyType)
                )
            )
    }

    override suspend fun getMyStudyInfo(): Flow<List<StudyInfoItem>> = flow {
        dataSource.getMyStudyInfo().collect {
            emit(it.dataBaseToDomain().items)
        }
    }

    override suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.insertMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.deleteMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun getRemoteUpdateState(
        dynastyType: String,
        studyType: String
    ): Flow<Boolean> = flow {
        dataSource.getRemoteUpdateState(dynastyType.checkedType(),studyType).collect {
            emit(it)
        }
    }

    override suspend fun setRemoteUpdateState(
        dynastyType: String,
        studyType: String,
        state: Boolean
    ) {
        dataSource.setRemoteUpdateState(dynastyType.checkedType(),studyType,state)
    }

    override suspend fun getGuideState(key: String): Flow<Boolean> = flow {
        dataSource.getGuideState(key).collect {
            emit(it)
        }
    }

    override suspend fun setGuideState(key: String) {
        dataSource.setGuideState(key)
    }
}