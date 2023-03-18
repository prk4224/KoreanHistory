package com.jaehong.presentation.ui.studytype

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.presentation.navigation.Destination
import com.jaehong.presentation.navigation.KoreanHistoryNavigator
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

    private val _dynastyType = MutableStateFlow("")
    val dynastyType = _dynastyType.asStateFlow()

    private val _animationState = MutableStateFlow(false)
    val animationState = _animationState.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyType.DYNASTY_TYPE_KEY)
        _dynastyType.value = dynastyType?: throw IllegalArgumentException("Dynasty Type Error")
    }

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _animationState.value = true
        }
    }

    fun onNavigateToStudyPageClicked(dynastyType: String, studyType: String) {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateTo(Destination.StudyPage(dynastyType,studyType))
        }
    }
}