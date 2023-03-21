package com.jaehong.data.remote.datasource

import com.jaehong.data.remote.model.RootField
import com.jaehong.data.remote.network.FireStoreService
import com.jaehong.data.util.safeApiCall
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.result.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val fireStoreService: FireStoreService
): RemoteDataSource {

    override suspend fun getRemoteStudyInfo(
        dynastyType: DynastyDetailType,
        studyType: String,
    ): Flow<ApiResult<RootField>> = flow {
        emit(safeApiCall { fireStoreService.getStudyInfo(dynastyType.value(studyType),studyType) })
    }
}