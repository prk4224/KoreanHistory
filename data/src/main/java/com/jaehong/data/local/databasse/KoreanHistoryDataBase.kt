package com.jaehong.data.local.databasse

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jaehong.data.local.databasse.converter.DateListConverters
import com.jaehong.data.local.databasse.dao.MyStudyDao
import com.jaehong.data.local.databasse.dao.StudyInfoDao
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity


@Database(
    entities = [
        MyStudyEntity::class,
        StudyInfoEntity::class
    ], version = 4
)
@TypeConverters(DateListConverters::class)
abstract class KoreanHistoryDataBase: RoomDatabase() {

    abstract fun myStudyDao(): MyStudyDao
    abstract fun studyInfoDao(): StudyInfoDao

    companion object {
        const val KOREAN_HISTORY_NAME = "KoreanHistoryApp.db"
    }
}