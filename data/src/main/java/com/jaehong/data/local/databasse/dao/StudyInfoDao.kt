package com.jaehong.data.local.databasse.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jaehong.data.local.databasse.entity.StudyInfoEntity

@Dao
interface StudyInfoDao {

    @Query("SELECT * FROM STUDY_INFO_TABLE WHERE pageType = :studyType")
    suspend fun getLocalStudyInfo(studyType: String): List<StudyInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalStudyInfo(info: StudyInfoEntity)

    @Transaction
    suspend fun insertStudyInfoWithListTransaction(studyInfoList: List<StudyInfoEntity>) {
        studyInfoList.forEach {
            insertLocalStudyInfo(it)
        }
    }
}