package com.jaehong.data.local.datasource

import com.jaehong.data.local.preference.KoreanHistoryPreference
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity
import com.jaehong.data.util.Constants.GUIDE_TOKEN
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val koreanHistoryDataBase: KoreanHistoryDataBase,
    private val preference: KoreanHistoryPreference,
): LocalDataSource {

    override suspend fun getLocalStudyInfo(studyType: String)
    : Flow<List<StudyInfoEntity>> = flow {
        emit(koreanHistoryDataBase.studyInfoDao().getLocalStudyInfo(studyType))
    }

    override suspend fun insertLocalStudyIndo(studyList: List<StudyInfoEntity>) {
        koreanHistoryDataBase.studyInfoDao().insertStudyInfoWithListTransaction(studyList)
    }

    override suspend fun getMyStudyInfo(): Flow<List<MyStudyEntity>> = flow {
         emit(koreanHistoryDataBase.myStudyDao().getMyStudyList())
    }

    override suspend fun insertMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().insertMyStudyWithListTransaction(studyList)
    }

    override suspend fun deleteMyStudyInfo(studyList: List<MyStudyEntity>) {
        koreanHistoryDataBase.myStudyDao().deleteMyStudyWithListTransaction(studyList)
    }

    override suspend fun getRemoteState(
        dynastyType: DynastyDetailType,
        studyType: String,
    ): Flow<Boolean> = flow {
        emit(preference.getState(dynastyType.value(studyType)))
    }

    override suspend fun setRemoteState(
        dynastyType: DynastyDetailType,
        studyType: String,
        state: Boolean
    ) {
        preference.setState(dynastyType.value(studyType),state)
    }

    override suspend fun getGuideInfo(key: String)
    : Flow<Boolean> = flow {
        emit(preference.getString(key))
    }

    override suspend fun setGuideInfo(key: String) {
        preference.setString(key, GUIDE_TOKEN)
    }
}