package com.jaehong.data.remote.datasource

import com.jaehong.data.remote.model.RemoteData
import com.jaehong.data.remote.model.RootField
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.result.NetworkResult
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getRemoteStudyInfo(
        dynastyType: DynastyDetailType,
        studyType: String): Flow<NetworkResult<RootField>>
}