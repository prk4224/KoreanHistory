package com.jaehong.presenter.ui.studypage

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.domain.local.usecase.GetStudyInfoUseCase
import com.jaehong.presenter.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudyPageViewModel @Inject constructor(
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

    private val _isVisible = MutableStateFlow(false)
    val isVisible = _isVisible.asStateFlow()

    private val _isAllHintVisible = MutableStateFlow(false)
    val isAllHintVisible = _isAllHintVisible.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _pagerList = MutableStateFlow(listOf(""))
    val pagerList = _pagerList.asStateFlow()

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

        _dynastyState.value = dynastyType ?: throw IllegalArgumentException("Dynasty Type Error")
        _studyState.value = studyType ?: throw IllegalArgumentException("Study Type Error")
        viewModelScope.launch {
            with(studyInfoUseCase) {
                this(dynastyType)
                    .catch { Log.d("Get All Data", "result: ${it.message}") }
                    .collect {
                        _allStudyInfoList.value = it
                        _pagerList.value = getPagerList(it)
                    }

                if (studyType == StudyType.FIRST_REVIEW.value) {
                    getStudyIngo(dynastyType)
                        .catch { Log.d("Study Type", "result: ${it.message}") }
                        .collect { _studyInfoList.value = it }
                    }

                getGuideInfo(GuideKey.USER_RULE_ORIGIN.value)
                    .catch { Log.d("First Guide Rule", "result: ${it.message}") }
                    .collect { _checkedUserRuleOrigin.value = it }
                getGuideInfo(GuideKey.USER_RULE_FIRST.value)
                    .catch { Log.d("Second Guide Rule", "result: ${it.message}") }
                    .collect { _checkedUserRuleFirst.value = it }
                getGuideInfo(GuideKey.USER_RULE_BLANK.value)
                    .catch { Log.d("Third Guide Rule", "result: ${it.message}") }
                    .collect { _checkedUserRuleBlank.value = it }
            }
        }
    }

    fun setUserRule(key: String) {
        viewModelScope.launch {
            studyInfoUseCase.setGuideInfo(key)
        }
    }

    fun updateLabel(label: Int, studyType: String) {
        when (studyType) {
            StudyType.ORIGIN_STUDY.value -> _originGuideLabel.value = label
            StudyType.FIRST_REVIEW.value -> _firstGuideLabel.value = label
            StudyType.ALL_BLANK_REVIEW.value -> _blankGuideLabel.value = false
        }
    }

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm() {
        addMyStudyInfo(allStudyInfoList.value)
        _showDialog.value = false
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun addSelectedItems(selectedItems: List<StudyInfoItem>) {
        addMyStudyInfo(selectedItems)
    }

    fun changeButtonState(itemsSize: Int) {
        _isVisible.value = itemsSize > 0
    }

    fun changeAllHintState() {
        _isAllHintVisible.value = _isAllHintVisible.value.not()
    }

    private fun initDataChangeButton() {
        _isVisible.value = false
    }

    private fun addMyStudyInfo(studyInfo: List<StudyInfoItem>) {
        viewModelScope.launch {
            studyInfoUseCase.insertMyStudyInfo(studyInfo)
            initDataChangeButton()
        }
    }

    private fun getPagerList(studyInfo : StudyInfo): List<String> {
        val pagerList = mutableListOf<String>()
        studyInfo.forEach {
            if (pagerList.contains(it.detail).not()) {
                pagerList.add(it.detail)
            }
        }
        return pagerList
    }
}