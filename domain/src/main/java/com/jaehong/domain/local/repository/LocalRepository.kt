package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

interface LocalRepository {
    suspend fun getAllStudyInfo(dynastyType: String, studyType: String): StudyInfo

    suspend fun gatStudyInfo(dynastyType: String, studyType: String): StudyInfo

    suspend fun gatMyStudyInfo(): List<StudyInfoItem>

    suspend fun addMyStudyInfo(studyList: List<StudyInfoItem>)
}