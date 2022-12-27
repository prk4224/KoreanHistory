package com.jaehong.koreanhistory.di

import com.jaehong.koreanhistory.navigation.KoreanHistoryNavigator
import com.jaehong.koreanhistory.navigation.KoreanHistoryNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Singleton
    @Binds
    fun bindKoreanHistoryNavigator(appNavigatorImpl: KoreanHistoryNavigatorImpl): KoreanHistoryNavigator
}
