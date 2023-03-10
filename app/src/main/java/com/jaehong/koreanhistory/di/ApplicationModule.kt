package com.jaehong.koreanhistory.di

import android.content.Context
import com.jaehong.data.local.GuidePreference
import com.jaehong.koreanhistory.Constants.GUIDE_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun providePreference(@ApplicationContext context: Context): GuidePreference =
        GuidePreference(context.getSharedPreferences(GUIDE_KEY, Context.MODE_PRIVATE))
}