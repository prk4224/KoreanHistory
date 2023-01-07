package com.jaehong.data.local.repository

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.mapper.Mapper.dataBaseToDomain
import com.jaehong.data.mapper.Mapper.dataToDomain
import com.jaehong.data.mapper.Mapper.domainToDataBase
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
): com.jaehong.domain.local.repository.LocalRepository {

    override suspend fun getAllStudyInfo(dynastyType: String, studyType: String): StudyInfo {
        return dataSource.getAllStudyInfo(dynastyType,studyType).dataToDomain()
    }

    override suspend fun gatStudyInfo(dynastyType: String, studyType: String): StudyInfo {
        return dataSource.gatStudyInfo(dynastyType,studyType).dataToDomain()
    }

    override suspend fun gatMyStudyInfo(): List<StudyInfoItem> {
        return dataSource.gatMyStudyInfo().dataBaseToDomain()
    }

    override suspend fun addMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.addMyStudyInfo(studyList.domainToDataBase())
    override suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>) {
        dataSource.deleteMyStudyInfo(studyList.domainToDataBase())
    }
}