package com.jaehong.presenter.ui.dynasty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DynastyViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator
): ViewModel() {

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _isVisible.value = true
        }
    }

    fun onBackButtonClicked() {
        koreanHistoryNavigator.tryNavigateBack()
    }

    fun onNavigateToStudyTypeClicked(dynastyType: String) {
        koreanHistoryNavigator.tryNavigateTo(Destination.StudyType(dynastyType))
    }

    fun onNavigateToMyStudyClicked() {
        koreanHistoryNavigator.tryNavigateTo(Destination.MyStudy())
    }
}