package com.jaehong.data.local.databasse.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jaehong.data.local.databasse.entity.MyStudyEntity

@Dao
interface MyStudyDao {

    @Query("SELECT * FROM MY_STUDY_TABLE ")
    suspend fun getMyStudyList(): List<MyStudyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyStudy(info: MyStudyEntity)

    @Transaction
    suspend fun insertMyStudyWithListTransaction(myStudyList: List<MyStudyEntity>) {
        myStudyList.forEach {
            insertMyStudy(it)
        }
    }

    @Delete
    suspend fun deleteMyStudy(info: MyStudyEntity)

    @Transaction
    suspend fun deleteMyStudyWithListTransaction(myStudyList: List<MyStudyEntity>) {
        myStudyList.forEach {
            deleteMyStudy(it)
        }
    }
}