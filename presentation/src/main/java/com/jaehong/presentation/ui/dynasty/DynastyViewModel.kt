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

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    init {
        viewModelScope.launch {
            getGuideInfoUseCase(GuideKey.GUIDE_KEY.value)
                .catch { Log.d("Guide Date", "result : ${it.message}") }
                .collect {
                    _showDialog.value = it
                }
        }
    }

    fun startAnimation(){
        viewModelScope.launch {
            delay(300)
            _isVisible.value = true
        }
    }

    fun onDialogDismiss(check: Boolean) {
        _showDialog.value = false
        viewModelScope.launch {
            if(check) getGuideInfoUseCase.setGuideInfo(GuideKey.GUIDE_KEY.value)
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