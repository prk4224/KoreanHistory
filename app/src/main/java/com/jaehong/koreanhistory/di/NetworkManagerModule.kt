package com.jaehong.koreanhistory.di

import android.content.Context
import com.jaehong.data.local.network_connect.NetworkManager
import com.jaehong.data.local.network_connect.NetworkManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkManagerModule {

    @Singleton
    @Provides
    fun provideNetworkManager(
        @ApplicationContext context: Context
    ): NetworkManager = NetworkManagerImpl(context)
}