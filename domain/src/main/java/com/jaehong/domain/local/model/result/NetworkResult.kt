package com.jaehong.domain.local.model.result

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    object Loading : NetworkResult<Nothing>()
    data class Error(val exception: Throwable) : NetworkResult<Nothing>()
}