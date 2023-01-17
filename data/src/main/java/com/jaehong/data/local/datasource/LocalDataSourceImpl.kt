package com.jaehong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.jaehong.data.local.GuidePreference
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.util.Constants.GO_LYEO
import com.jaehong.data.util.Constants.GUIDE_TOKEN
import com.jaehong.data.util.Constants.JO_SEON
import com.jaehong.data.util.Constants.SAM_GUG
import com.jaehong.data.util.Constants.SIN_LA
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val koreanHistoryDataBase: KoreanHistoryDataBase,
    private val preference: GuidePreference
): LocalDataSource {

    override suspend fun getAllStudyInfo(dynastyType: String): StudyEntity {

        val json = when(dynastyType) {
            SAM_GUG -> context.assets.open("samkok_all.json").reader().readText()
            SIN_LA -> context.assets.open("hoosamkok_all.json").reader().readText()
            GO_LYEO -> context.assets.open("golyeo_all.json").reader().readText()
            JO_SEON -> context.assets.open("joseon_all.json").reader().readText()
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }

        return Gson().fromJson(json, StudyEntity::class.java)
    }

    override suspend fun getStudyInfo(dynastyType: String, studyType: String): StudyEntity {

        val json = when(dynastyType) {
            SAM_GUG -> context.assets.open("samkok_first_letter.json").reader().readText()
            SIN_LA  -> context.assets.open("hoosamkok_first_letter.json").reader().readText()
            GO_LYEO -> context.assets.open("golyeo_first_letter.json").reader().readText()
            JO_SEON -> context.assets.open("joseon_first_letter.json").reader().readText()
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }
        return Gson().fromJson(json, StudyEntity::class.java)
    }

    override suspend fun gatMyStudyInfo(): List<MyStudyEntity> {
        return koreanHistoryDataBase.myStudyDao().getMyStudyList()
    }

    override suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().insertMyStudyWithListTransaction(studyList)
    }

    override suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().deleteMyStudyWithListTransaction(studyList)
    }

    override suspend fun getGuideInfo(key: String): Boolean {
        return preference.getString(key)
    }

    override suspend fun setGuideInfo(key: String) {
        preference.setString(key, GUIDE_TOKEN)
    }


}