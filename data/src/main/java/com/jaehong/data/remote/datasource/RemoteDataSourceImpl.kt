package com.jaehong.data.remote.datasource

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.jaehong.data.remote.model.StudyEntity
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fireStore: FirebaseFirestore
): RemoteDataSource {

    private val TAG = "RemoteDataSourceImpl"

    override suspend fun getRemoteStudyInfo(
        dynastyType: DynastyDetailType,
        studyType: String,
    ): Flow<StudyEntity> = flow {
        var items: StudyEntity? =null

        fireStore.collection(studyType)
            .document(dynastyType.value(studyType))
            .get()
            .addOnSuccessListener { data ->
                items = data.toObject<StudyEntity>()
            }
            .addOnFailureListener { Log.d(TAG, "$it") }
            .await()

        items?.let { emit(it) }
    }
}