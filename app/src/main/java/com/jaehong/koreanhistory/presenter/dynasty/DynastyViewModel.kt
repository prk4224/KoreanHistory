package com.jaehong.koreanhistory.presenter.dynasty

import androidx.lifecycle.ViewModel
import com.jaehong.koreanhistory.navigation.Destination
import com.jaehong.koreanhistory.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DynastyViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator
): ViewModel() {

    fun onBackButtonClicked() {
        koreanHistoryNavigator.tryNavigateBack()
    }

    fun onNavigateToStudyTypeClicked(dynastyType: String) {
        koreanHistoryNavigator.tryNavigateTo(Destination.StudyType(dynastyType))
    }
}