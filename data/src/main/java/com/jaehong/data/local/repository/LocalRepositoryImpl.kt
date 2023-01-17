package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.mapper.Mapper.dataBaseToDomain
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.data.mapper.Mapper.domainToDataBase
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): com.jaehong.domain.local.repository.LocalRepository {

    override suspend fun getAllStudyInfo(dynastyType: String): StudyInfo {
        return dataSource.getAllStudyInfo(dynastyType).dataToDomain()
    }

    override suspend fun getStudyInfo(dynastyType: String, studyType: String): StudyInfo {
        return dataSource.getStudyInfo(dynastyType,studyType).dataToDomain()
    }

    override suspend fun gatMyStudyInfo(): List<StudyInfoItem> {
        return dataSource.gatMyStudyInfo().dataBaseToDomain()
    }

    override suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.insertMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.deleteMyStudyInfo(studyList.domainToDataBase())
    }

    override suspend fun getGuideInfo(key: String): Boolean {
        return dataSource.getGuideInfo(key)
    }

    override suspend fun setGuideInfo(key: String) {
        dataSource.setGuideInfo(key)
    }
}