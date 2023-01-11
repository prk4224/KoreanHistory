package com.jaehong.data.local.datasource

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity

interface LocalDataSource {
    suspend fun getAllStudyInfo(dynastyType: String): StudyEntity

    suspend fun getStudyInfo(dynastyType: String, studyType: String): StudyEntity

    suspend fun gatMyStudyInfo(): List<MyStudyEntity>

    suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>)

    suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>)
}