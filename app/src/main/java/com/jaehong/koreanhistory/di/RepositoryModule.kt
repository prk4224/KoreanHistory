package com.jaehong.koreanhistory.di

import com.jaehong.data.local.datasource.LocalDataSource
import com.jaehong.data.local.datasource.LocalDataSourceImpl
import com.jaehong.data.local.repository.LocalRepositoryImpl
import com.jaehong.domain.local.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

    @Singleton
    @Binds
    fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}