package com.jaehong.data.local.network_connect

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest

interface NetworkManager {
    fun getConnectivityManager(): ConnectivityManager
    fun getNetworkRequest(): NetworkRequest
}