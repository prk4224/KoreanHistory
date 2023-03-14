package com.jaehong.data.local.datasource

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.jaehong.data.local.GuidePreference
import com.jaehong.data.local.databasse.KoreanHistoryDataBase
import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.util.Constants.GUIDE_TOKEN
import com.jaehong.data.util.Constants.STUDY_TYPE_ALL
import com.jaehong.data.util.Constants.STUDY_TYPE_FIRST
import com.jaehong.data.util.enum_type.DynastyDetailType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val koreanHistoryDataBase: KoreanHistoryDataBase,
    private val preference: GuidePreference,
    private val fireStore: FirebaseFirestore
): LocalDataSource {

    private val TAG = "LocalDataSourceImpl"

    override suspend fun getAllStudyInfo(
        dynastyType: DynastyDetailType
    ): Flow<StudyEntity> = flow {
        var items: StudyEntity? =null

        fireStore.collection(STUDY_TYPE_ALL)
            .document(getDocumentFirst(dynastyType))
            .get()
            .addOnSuccessListener { data ->
                items = data.toObject<StudyEntity>()
            }
            .addOnFailureListener { Log.d(TAG, "$it") }
            .await()

        items?.let { emit(it) }
    }

    override suspend fun getStudyInfo(
        dynastyType: DynastyDetailType
    ): Flow<StudyEntity> = flow {
        var items: StudyEntity? =null

        fireStore.collection(STUDY_TYPE_FIRST)
            .document(getDocumentFirst(dynastyType))
            .get()
            .addOnSuccessListener { data ->
                items = data.toObject<StudyEntity>()
            }
            .addOnFailureListener { Log.d(TAG, "$it") }
            .await()

        items?.let { emit(it) }
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


    private fun getDocumentAll(dynastyType: DynastyDetailType): String {
        return when(dynastyType) {
            DynastyDetailType.SAM_GUG -> "samkok_all"
            DynastyDetailType.SIN_LA -> "hoosamkok_all"
            DynastyDetailType.GO_LYEO -> "golyeo_all"
            DynastyDetailType.JO_SEON -> "joseon_all"
            DynastyDetailType.MODERN_ONE -> "modern_all_1"
            DynastyDetailType.MODERN_TWO -> "modern_all_2"
            DynastyDetailType.MODERN_THREE -> "modern_all_3"
            DynastyDetailType.MODERN_FOUR -> "modern_all_4"
            DynastyDetailType.JAPANESE_ONE -> "japanese_all_1"
            DynastyDetailType.JAPANESE_TWO -> "japanese_all_2"
            DynastyDetailType.JAPANESE_THREE -> "japanese_all_3"
            DynastyDetailType.CONTEMPORARY_ONE -> "contemporary_all_1"
            DynastyDetailType.CONTEMPORARY_TWO -> "contemporary_all_2"
            DynastyDetailType.CONTEMPORARY_THREE -> "contemporary_all_3"
        }
    }

    private fun getDocumentFirst(dynastyType: DynastyDetailType): String {
        return when(dynastyType) {
            DynastyDetailType.SAM_GUG -> "samkok_first_letter"
            DynastyDetailType.SIN_LA -> "hoosamkok_first_letter"
            DynastyDetailType.GO_LYEO -> "golyeo_first_letter"
            DynastyDetailType.JO_SEON -> "joseon_first_letter"
            DynastyDetailType.MODERN_ONE -> "modern_first_letter_1"
            DynastyDetailType.MODERN_TWO -> "modern_first_letter_2"
            DynastyDetailType.MODERN_THREE -> "modern_first_letter_3"
            DynastyDetailType.MODERN_FOUR -> "modern_first_letter_4"
            DynastyDetailType.JAPANESE_ONE -> "japanese_first_letter_1"
            DynastyDetailType.JAPANESE_TWO -> "japanese_first_letter_2"
            DynastyDetailType.JAPANESE_THREE -> "japanese_first_letter_3"
            DynastyDetailType.CONTEMPORARY_ONE -> "contemporary_first_letter_1"
            DynastyDetailType.CONTEMPORARY_TWO -> "contemporary_first_letter_2"
            DynastyDetailType.CONTEMPORARY_THREE -> "contemporary_first_letter_3"
        }
    }
}