package com.jaehong.presenter.ui.mystudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.usecase.GetMyStudyInfoUseCase
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyStudyViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    private val myStudyInfoUseCase: GetMyStudyInfoUseCase
) : ViewModel() {

    private val initList: MutableList<StudyInfoItem> = mutableListOf()

    private val _myStudyInfoList = MutableStateFlow(initList.toList())
    val myStudyInfoList = _myStudyInfoList.asStateFlow()

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _selectedItems = MutableStateFlow(initList)
    val selectedItems = _selectedItems.asStateFlow()

    init {
        viewModelScope.launch {
            _myStudyInfoList.value = myStudyInfoUseCase()
        }
    }

    fun addSelectedItem(studyInfoItem: StudyInfoItem){
        _selectedItems.value.add(studyInfoItem)
    }

    fun removeSelectedItem(studyInfoItem: StudyInfoItem){
        _selectedItems.value.remove(studyInfoItem)
    }

    fun changeButtonState(state: Boolean){
        _isVisible.value = state
    }

    fun onBackButtonClicked() {
        koreanHistoryNavigator.tryNavigateBack()
    }
}