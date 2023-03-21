package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.checkedType
import com.jaehong.data.mapper.Mapper.dataFromDomain
import com.jaehong.data.mapper.Mapper.domainFromData
import com.jaehong.data.mapper.Mapper.domainFromDataBase
import com.jaehong.data.mapper.Mapper.mappingListDataFromDomain
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.result.DbResult
import com.jaehong.domain.local.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : LocalRepository {
    override suspend fun getLocalStudyInfo(
        dynastyType: String,
        studyType: String
    ): Flow<DbResult<List<StudyInfoItem>>> = flow {

        dataSource.getLocalStudyInfo(dynastyType.checkedType().value(studyType))
            .collect {
            emit(it.dataFromDomain())
        }
    }

    override suspend fun insertLocalStudyIndo(
        studyList: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String
    ): Flow<DbResult<Boolean>> = flow {
        dataSource.insertLocalStudyIndo(
            studyList.domainFromData(
                dynastyType.checkedType().value(studyType)
            )
        ).collect {
            emit(it)
        }

    }

    override suspend fun getMyStudyInfo()
    : Flow<DbResult<List<StudyInfoItem>>> = flow {
        dataSource.getMyStudyInfo().collect {
            emit(it.mappingListDataFromDomain())
        }
    }

    override suspend fun insertMyStudyInfo(
        studyList: List<StudyInfoItem>
    ): Flow<DbResult<Boolean>> = flow {
        dataSource
            .insertMyStudyInfo(studyList.domainFromDataBase())
            .collect {
                emit(it)
            }
    }

    override suspend fun deleteMyStudyInfo(
        studyList: List<StudyInfoItem>
    ): Flow<DbResult<Boolean>> = flow {
        dataSource
            .deleteMyStudyInfo(studyList.domainFromDataBase())
            .collect {
                emit(it)
            }
    }

    override suspend fun getRemoteUpdateState(
        dynastyType: String,
        studyType: String
    ): Flow<Boolean> = flow {
        dataSource.getRemoteUpdateState(dynastyType.checkedType(), studyType).collect {
            emit(it)
        }
    }

    override suspend fun setRemoteUpdateState(
        dynastyType: String,
        studyType: String,
        state: Boolean
    ) {
        dataSource.setRemoteUpdateState(dynastyType.checkedType(), studyType, state)
    }

    override suspend fun getGuideState(key: String): Flow<Boolean> = flow {
        dataSource.getGuideState(key).collect {
            emit(it)
        }
    }

    override suspend fun setGuideState(key: String) {
        dataSource.setGuideState(key)
    }

    override suspend fun observeConnectivityAsFlow(): Flow<Boolean> = flow {
        dataSource.observeConnectivityAsFlow().collect {
            emit(it)
        }
    }
}