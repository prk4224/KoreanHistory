package com.jaehong.presentation.ui.dynasty

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.usecase.GetGuideInfoUseCase
import com.jaehong.presentation.navigation.Destination
import com.jaehong.presentation.navigation.KoreanHistoryNavigator
import com.jaehong.domain.local.model.enum_type.GuideKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DynastyViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    private val getGuideInfoUseCase: GetGuideInfoUseCase
): ViewModel() {

    private val _animationState = MutableStateFlow(false)
    val animationState = _animationState.asStateFlow()

    private val _isVisibleDialog = MutableStateFlow(false)
    val isVisibleDialog = _isVisibleDialog.asStateFlow()

    init {
        viewModelScope.launch {
            getGuideInfoUseCase(GuideKey.GUIDE_KEY.value)
                .catch { Log.d("Guide Date", "result : ${it.message}") }
                .collect {
                    _isVisibleDialog.value = it
                }
        }
    }

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _animationState.value = true
        }
    }

    fun onDialogDismiss(check: Boolean) {
        _isVisibleDialog.value = false
        viewModelScope.launch {
            if(check) getGuideInfoUseCase.setGuideState(GuideKey.GUIDE_KEY.value)
        }
    }

    fun onNavigateToStudyTypeClicked(dynastyType: String) {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateTo(Destination.StudyType(dynastyType))
        }
    }

    fun onNavigateToTypeCheckClicked(dynastyType: String) {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateTo(Destination.TypeCheck(dynastyType))
        }
    }

    fun onNavigateToMyStudyClicked() {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateTo(Destination.MyStudy())
        }
    }
}