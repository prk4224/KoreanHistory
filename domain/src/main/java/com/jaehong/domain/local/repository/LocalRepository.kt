package com.jaehong.domain.local.repository

import com.jaehong.domain.local.model.UiStudyInfo

interface LocalRepository {
    suspend fun getStudyInfo(): UiStudyInfo
}