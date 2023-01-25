package com.jaehong.data.local.datasource

import android.content.Context
import com.google.gson.Gson
import com.jaehong.data.local.GuidePreference
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.util.Constants.GUIDE_TOKEN
import com.jaehong.data.util.enum_type.DynastyDetailType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val koreanHistoryDataBase: KoreanHistoryDataBase,
    private val preference: GuidePreference
): LocalDataSource {

    override suspend fun getAllStudyInfo(
        dynastyType: DynastyDetailType
    ): Flow<StudyEntity> = flow {

        val json = when(dynastyType) {
            DynastyDetailType.SAM_GUG -> context.assets.open("samkok_all.json").reader().readText()
            DynastyDetailType.SIN_LA -> context.assets.open("hoosamkok_all.json").reader().readText()
            DynastyDetailType.GO_LYEO -> context.assets.open("golyeo_all.json").reader().readText()
            DynastyDetailType.JO_SEON -> context.assets.open("joseon_all.json").reader().readText()
            DynastyDetailType.MODERN_ONE -> context.assets.open("modern_all_1.json").reader().readText()
            DynastyDetailType.MODERN_TWO -> context.assets.open("modern_all_2.json").reader().readText()
            DynastyDetailType.MODERN_THREE -> context.assets.open("modern_all_3.json").reader().readText()
            DynastyDetailType.MODERN_FOUR -> context.assets.open("modern_all_4.json").reader().readText()
            DynastyDetailType.JAPANESE_ONE -> context.assets.open("japanese_all_1.json").reader().readText()
            DynastyDetailType.JAPANESE_TWO -> context.assets.open("japanese_all_2.json").reader().readText()
            DynastyDetailType.JAPANESE_THREE -> context.assets.open("japanese_all_3.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_ONE -> context.assets.open("contemporary_all_1.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_TWO -> context.assets.open("contemporary_all_2.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_THREE -> context.assets.open("contemporary_all_4.json").reader().readText()
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }

        emit(Gson().fromJson(json, StudyEntity::class.java))
    }

    override suspend fun getStudyInfo(
        dynastyType: DynastyDetailType
    ): Flow<StudyEntity> = flow {
        val json = when(dynastyType) {
            DynastyDetailType.SAM_GUG -> context.assets.open("samkok_first_letter.json").reader().readText()
            DynastyDetailType.SIN_LA  -> context.assets.open("hoosamkok_first_letter.json").reader().readText()
            DynastyDetailType.GO_LYEO -> context.assets.open("golyeo_first_letter.json").reader().readText()
            DynastyDetailType.JO_SEON -> context.assets.open("joseon_first_letter.json").reader().readText()
            DynastyDetailType.MODERN_ONE -> context.assets.open("modern_first_letter_1.json").reader().readText()
            DynastyDetailType.MODERN_TWO -> context.assets.open("modern_first_letter_2.json").reader().readText()
            DynastyDetailType.MODERN_THREE -> context.assets.open("modern_first_letter_3.json").reader().readText()
            DynastyDetailType.MODERN_FOUR -> context.assets.open("modern_first_letter_4.json").reader().readText()
            DynastyDetailType.JAPANESE_ONE -> context.assets.open("japanese_first_letter_1.json").reader().readText()
            DynastyDetailType.JAPANESE_TWO -> context.assets.open("japanese_first_letter_2.json").reader().readText()
            DynastyDetailType.JAPANESE_THREE -> context.assets.open("japanese_first_letter_3.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_ONE -> context.assets.open("contemporary_first_letter_1.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_TWO -> context.assets.open("contemporary_first_letter_2.json").reader().readText()
            DynastyDetailType.CONTEMPORARY_THREE -> context.assets.open("contemporary_first_letter    _4.json").reader().readText()
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }
        emit(Gson().fromJson(json, StudyEntity::class.java))
    }

    override suspend fun gatMyStudyInfo(): Flow<List<MyStudyEntity>> = flow {
         emit(koreanHistoryDataBase.myStudyDao().getMyStudyList())
    }

    override suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().insertMyStudyWithListTransaction(studyList)
    }

    override suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().deleteMyStudyWithListTransaction(studyList)
    }

    override suspend fun getGuideInfo(
        key: String
    ): Flow<Boolean> = flow {
        emit(preference.getString(key))
    }

    override suspend fun setGuideInfo(key: String) {
        preference.setString(key, GUIDE_TOKEN)
    }


}