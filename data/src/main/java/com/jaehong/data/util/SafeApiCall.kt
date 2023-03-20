package com.jaehong.data.util

import com.jaehong.domain.local.model.result.NetworkResult
import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T?>): NetworkResult<T> {
    val response: Response<T?>

    try {
        response = call()
    } catch (e: IOException) {
        return NetworkResult.Error(IOException(e.message ?: "Internet error runs"))
    }

    val body = response.body()
    return if (body != null) {
        NetworkResult.Success(body)
    } else {
        NetworkResult.Error(IOException(""))
    }
}