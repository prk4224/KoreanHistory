package com.jaehong.koreanhistory.di

import android.content.Context
import androidx.room.Room
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.KoreanHistoryDataBase.Companion.KOREAN_HISTORY_NAME
import com.jaehong.data.local.databasse.dao.MyStudyDao
import com.jaehong.data.local.databasse.dao.StudyInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideKoreanHistoryDataBase(
        @ApplicationContext context: Context
    ): KoreanHistoryDataBase {
        return Room.databaseBuilder(
            context, KoreanHistoryDataBase::class.java, KOREAN_HISTORY_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMyStudy(
        dataBase: KoreanHistoryDataBase
    ): MyStudyDao = dataBase.myStudyDao()

    @Singleton
    @Provides
    fun provideStudyInfo(
        dataBase: KoreanHistoryDataBase
    ): StudyInfoDao = dataBase.studyInfoDao()

}