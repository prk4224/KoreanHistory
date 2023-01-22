package com.jaehong.presenter.ui.dynasty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.usecase.GetGuideInfoUseCase
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import com.jaehong.presenter.util.enum.GuideKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
            _showDialog.value = getGuideInfoUseCase(GuideKey.GUIDE_KEY.value)
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
        koreanHistoryNavigator.tryNavigateTo(Destination.StudyType(dynastyType))
    }

    fun onNavigateToMyStudyClicked() {
        koreanHistoryNavigator.tryNavigateTo(Destination.MyStudy())
    }
}