package com.jaehong.data.local.databasse.dao

import androidx.room.*
import com.jaehong.data.local.databasse.entity.MyStudyEntity

@Dao
interface MyStudyDao {

    @Query("SELECT * FROM MY_STUDY_TABLE ")
    suspend fun getMyStudyList(): List<MyStudyEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMyStudy(info: MyStudyEntity): Long

    @Transaction
    suspend fun insertMyStudyWithListTransaction(myStudyList: List<MyStudyEntity>): Boolean {
        var result = 0
        myStudyList.forEach {
            if(insertMyStudy(it) > 0) result++
        }
        return result == myStudyList.size
    }

    @Delete
    suspend fun deleteMyStudy(info: MyStudyEntity): Int

    @Transaction
    suspend fun deleteMyStudyWithListTransaction(myStudyList: List<MyStudyEntity>): Boolean {
        var result = 0
        myStudyList.forEach {
            if(deleteMyStudy(it) > 0) result++
        }

        return result == myStudyList.size
    }
}