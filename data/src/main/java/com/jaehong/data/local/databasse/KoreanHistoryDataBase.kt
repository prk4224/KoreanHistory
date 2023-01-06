package com.jaehong.data.local.databasse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jaehong.data.local.databasse.dao.MyStudyDao
import com.jaehong.data.local.databasse.entity.MyStudyEntity


@Database(
    entities = [
        MyStudyEntity::class
    ],
    version = 1
)
abstract class KoreanHistoryDataBase: RoomDatabase() {

    abstract fun myStudyDao(): MyStudyDao

    companion object {
        const val KOREAN_HISTORY_NAME = "KoreanHistoryApp.db"
    }
}