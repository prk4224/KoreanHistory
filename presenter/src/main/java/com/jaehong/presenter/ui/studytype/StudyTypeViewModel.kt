package com.jaehong.presenter.ui.studytype

import androidx.lifecycle.SavedStateHandle
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
class StudyTypeViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _dynastyState = MutableStateFlow("")
    val dynastyState = _dynastyState.asStateFlow()
    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyType.DYNASTY_TYPE_KEY)
        _dynastyState.value = dynastyType?: throw IllegalArgumentException("Dynasty Type Error")
    }

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _isVisible.value = true
        }
    }

    fun onBackButtonClicked() {
        koreanHistoryNavigator.tryNavigateBack()
    }

    fun onNavigateToStudyPageClicked(dynastyType: String, studyType: String) {
        koreanHistoryNavigator.tryNavigateTo(Destination.StudyPage(dynastyType,studyType,"0"))
    }
}