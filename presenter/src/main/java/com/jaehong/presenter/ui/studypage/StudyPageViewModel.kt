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
import kotlinx.coroutines.flow.StateFlow
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

    private val _isAllHintVisible = MutableStateFlow(false)
    val isAllHintVisible = _isAllHintVisible.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _pagerList = MutableStateFlow(listOf(""))
    val pagerList = _pagerList.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage = _currentPage.asStateFlow()

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
            _pagerList.value = getPagerList()
        }
    }

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        _showDialog.value = false
        addMyStudyInfo(selectedItems.value)
        onNavigateReFresh()
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun updatePage(page: Int){
        _currentPage.value = page
        _selectedItems.value.clear()
        _isVisible.value = false
    }

    private fun addMyStudyInfo(studyInfo: List<StudyInfoItem>){
        viewModelScope.launch {
            studyInfoUseCase.insertMyStudyInfo(studyInfo)
        }
    }

    private fun getPagerList(): List<String> {
        val pagerList = mutableListOf<String>()
        allStudyInfoList.value.forEach {
            if(pagerList.contains(it.detail).not()){
                pagerList.add(it.detail)
            }
        }
        return pagerList
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

    fun changeAllHintState() {
        _isAllHintVisible.value = _isAllHintVisible.value.not()
    }

    private fun onNavigateReFresh() {
        koreanHistoryNavigator.tryNavigateBack()
        koreanHistoryNavigator.tryNavigateTo(Destination.StudyPage(dynastyState.value,studyState.value))
    }
}