package com.jaehong.presenter.ui.studypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.usecase.GetStudyInfoUseCase
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyPageViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    savedStateHandle: SavedStateHandle,
    private val studyInfoUseCase: GetStudyInfoUseCase
) : ViewModel() {

    private val _dynastyState = MutableStateFlow("")
    val dynastyState = _dynastyState.asStateFlow()

    private val _studyState = MutableStateFlow("")
    val studyState = _studyState.asStateFlow()

    private val _allStudyInfoList = MutableStateFlow(StudyInfo())
    val allStudyInfoList = _allStudyInfoList.asStateFlow()

    private val _studyInfoList = MutableStateFlow(StudyInfo())
    val studyInfoList = _studyInfoList.asStateFlow()

    private val initList: MutableList<StudyInfoItem> = mutableListOf()
    private val _selectedItems = MutableStateFlow(initList)
    val selectedItems = _selectedItems.asStateFlow()

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyPage.DYNASTY_TYPE_KEY)
        val studyType = savedStateHandle.get<String>(Destination.StudyPage.STUDY_TYPE_KEY)

        _dynastyState.value = dynastyType ?: throw IllegalArgumentException("Dynasty Type Error")
        _studyState.value = studyType ?: throw IllegalArgumentException("Study Type Error")
        viewModelScope.launch {
            _allStudyInfoList.value = studyInfoUseCase(dynastyType,studyType)

            if(studyType != ORIGIN_STUDY) {
                _studyInfoList.value = studyInfoUseCase.getStudyIngo(dynastyType,studyType)
            }
        }
    }

    fun addMyStudyInfo(studyInfo: List<StudyInfoItem>){
        viewModelScope.launch {
            studyInfoUseCase.insertMyStudyInfo(studyInfo)
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