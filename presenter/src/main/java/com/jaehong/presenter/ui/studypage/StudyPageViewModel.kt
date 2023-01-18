package com.jaehong.presenter.ui.studypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.usecase.GetStudyInfoUseCase
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY
import com.jaehong.presenter.util.Constants.USER_RULE_BLANK
import com.jaehong.presenter.util.Constants.USER_RULE_FIRST
import com.jaehong.presenter.util.Constants.USER_RULE_ORIGIN
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
    private val selectedItems = _selectedItems.asStateFlow()

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

    private val _originGuideLabel = MutableStateFlow(0)
    val originGuideLabel = _originGuideLabel.asStateFlow()

    private val _checkedUserRuleOrigin = MutableStateFlow(false)
    val checkedUserRuleOrigin = _checkedUserRuleOrigin.asStateFlow()

    private val _firstGuideLabel = MutableStateFlow(0)
    val firstGuideLabel = _firstGuideLabel.asStateFlow()

    private val _checkedUserRuleFirst = MutableStateFlow(false)
    val checkedUserRuleFirst = _checkedUserRuleFirst.asStateFlow()

    private val _blankGuideLabel = MutableStateFlow(true)
    val blankGuideLabel = _blankGuideLabel.asStateFlow()

    private val _checkedUserRuleBlank = MutableStateFlow(false)
    val checkedUserRuleBlank = _checkedUserRuleBlank.asStateFlow()



    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyPage.DYNASTY_TYPE_KEY)
        val studyType = savedStateHandle.get<String>(Destination.StudyPage.STUDY_TYPE_KEY)
        val startPage = savedStateHandle.get<String>(Destination.StudyPage.START_PAGE_KEY)

        _dynastyState.value = dynastyType ?: throw IllegalArgumentException("Dynasty Type Error")
        _studyState.value = studyType ?: throw IllegalArgumentException("Study Type Error")
        _currentPage.value = startPage?.toInt() ?: throw IllegalArgumentException("Start Page Error")

        viewModelScope.launch {
            _allStudyInfoList.value = studyInfoUseCase(dynastyType)
            if(studyType == FIRST_REVIEW) {
                _studyInfoList.value = studyInfoUseCase.getStudyIngo(dynastyType,studyType)
            }
            _pagerList.value = getPagerList()
            _checkedUserRuleOrigin.value = studyInfoUseCase.getGuideInfo(USER_RULE_ORIGIN)
            _checkedUserRuleFirst.value = studyInfoUseCase.getGuideInfo(USER_RULE_FIRST)
            _checkedUserRuleBlank.value = studyInfoUseCase.getGuideInfo(USER_RULE_BLANK)

        }
    }

    fun setUserRule(key: String) {
        viewModelScope.launch {
            studyInfoUseCase.setGuideInfo(key)
        }
    }

    fun updateLabel(label: Int, studyType: String) {
        when(studyType) {
            ORIGIN_STUDY -> _originGuideLabel.value = label
            FIRST_REVIEW -> _firstGuideLabel.value = label
            ALL_BLANK_REVIEW -> _blankGuideLabel.value = false
        }
    }

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        addMyStudyInfo(selectedItems.value)
        onNavigateRefreshClicked()
        _showDialog.value = false
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun updatePage(page: Int){
        _currentPage.value = page
        clearSelectedItems()
    }

    fun changeSelectedItem(studyInfoItem: StudyInfoItem, check: Boolean){
        if (check) _selectedItems.value.add(studyInfoItem)
        else _selectedItems.value.remove(studyInfoItem)
    }

    fun changeButtonState(){
        _isVisible.value = selectedItems.value.size > 0
    }

    fun changeAllHintState() {
        _isAllHintVisible.value = _isAllHintVisible.value.not()
    }

    private fun clearSelectedItems(){
        _isVisible.value = false
        _selectedItems.value.clear()
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

    private fun onNavigateRefreshClicked() {
        koreanHistoryNavigator.tryNavigateBack()
        koreanHistoryNavigator.tryNavigateTo(
            Destination.StudyPage(dynastyState.value,studyState.value,"${currentPage.value}")
        )
    }
}