package com.jaehong.koreanhistory.di

import com.jaehong.koreanhistory.navigation.KoreanHistoryNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Singleton
    @Provides
    fun provideKoreanHistoryNavigator(): KoreanHistoryNavigator = KoreanHistoryNavigator()
}
