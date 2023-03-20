package com.jaehong.data.remote.network


import com.jaehong.data.remote.model.RootField
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FireStoreService {

    @GET("documents/{studyType}/{dynastyType}")
    suspend fun getStudyInfo(
        @Path("dynastyType") dynastyType: String,
        @Path("studyType") studyType: String,
    ): Response<RootField?>
}