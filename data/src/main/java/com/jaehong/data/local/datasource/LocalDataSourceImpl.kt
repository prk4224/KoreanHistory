package com.jaehong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.util.Constants.FIRST_REVIEW
import com.jaehong.data.util.Constants.ORIGIN_STUDY
import com.jaehong.data.util.Constants.SAM_GUG
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val koreanHistoryDataBase: KoreanHistoryDataBase
): LocalDataSource {

    override suspend fun getAllStudyInfo(dynastyType: String, studyType: String): StudyEntity {

        val json = when(dynastyType) {
            SAM_GUG -> context.assets.open("samkok_all.json").reader().readText()
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }

        val data = Gson().fromJson(json, StudyEntity::class.java)
        return data
    }

    override suspend fun gatStudyInfo(dynastyType: String, studyType: String): StudyEntity {

        val json = when {
            dynastyType == SAM_GUG && studyType == ORIGIN_STUDY -> {
                context.assets.open("samkok_all.json").reader().readText()
            }
            dynastyType == SAM_GUG && studyType == FIRST_REVIEW  -> {
                context.assets.open("samkok_first_letter.json").reader().readText()
            }
            else -> {
                throw IllegalArgumentException("Dynasty Type Error")
            }
        }
        val data = Gson().fromJson(json, StudyEntity::class.java)
        return data
    }

    override suspend fun gatMyStudyInfo(): List<MyStudyEntity> {
        return koreanHistoryDataBase.myStudyDao().getMyStudyList()
    }

    override suspend fun addMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().insertMyStudyWithListTransaction(studyList)
    }

    override suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().deleteMyStudyWithListTransaction(studyList)
    }

}