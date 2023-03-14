package com.jaehong.data.local.datasource

import com.jaehong.data.local.GuidePreference
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.util.Constants.GUIDE_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val koreanHistoryDataBase: KoreanHistoryDataBase,
    private val preference: GuidePreference,
): LocalDataSource {

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