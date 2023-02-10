package com.jaehong.presentation.ui.modern_after

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
class TypeCheckViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    savedStateHandle: SavedStateHandle,
): ViewModel() {


    private val _dynastyState = MutableStateFlow("")
    val dynastyState = _dynastyState.asStateFlow()

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.TypeCheck.DYNASTY_TYPE_KEY)
        _dynastyState.value = dynastyType?: throw IllegalArgumentException("Dynasty Type Error")
    }

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _isVisible.value = true
        }
    }

    fun onNavigateToStudyTypeClicked(dynastyType: String) {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateTo(Destination.StudyType(dynastyType))
        }
    }
}