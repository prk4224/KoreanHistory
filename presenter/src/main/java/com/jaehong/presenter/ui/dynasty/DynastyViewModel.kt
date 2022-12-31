package com.jaehong.presenter.ui.dynasty

import androidx.lifecycle.ViewModel
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
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