package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

interface LocalRepository {
    suspend fun getAllStudyInfo(dynastyType: String): StudyInfo

    suspend fun getStudyInfo(dynastyType: String, studyType: String): StudyInfo

    suspend fun gatMyStudyInfo(): List<StudyInfoItem>

    suspend fun insertMyStudyInfo(studyList: List<StudyInfoItem>)

    suspend fun deleteMyStudyInfo(studyList: List<StudyInfoItem>)
}