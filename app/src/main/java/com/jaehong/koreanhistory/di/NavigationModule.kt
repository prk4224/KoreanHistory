package com.jaehong.koreanhistory.di

import com.jaehong.presentation.navigation.KoreanHistoryNavigator
import com.jaehong.presentation.navigation.KoreanHistoryNavigatorImpl
import dagger.Binds
import dagger.Module
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
