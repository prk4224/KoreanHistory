package com.jaehong.presentation.ui.studypage

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.domain.local.model.result.NetworkResult
import com.jaehong.domain.local.usecase.GetStudyInfoUseCase
import com.jaehong.presentation.navigation.Destination
import com.jaehong.presentation.util.Constants.STUDY_TYPE_ALL
import com.jaehong.presentation.util.Constants.STUDY_TYPE_FIRST
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val emptyList: List<StudyInfoItem> = listOf()

    private val _dynastyType = MutableStateFlow("")
    val dynastyType = _dynastyType.asStateFlow()

    private val _studyType = MutableStateFlow("")
    val studyType = _studyType.asStateFlow()

    private val _remoteState = MutableStateFlow(false)
    private val remoteState = _remoteState.asStateFlow()

    private val _originStudyItems = MutableStateFlow(emptyList)
    val originStudyItems = _originStudyItems.asStateFlow()

    private val _firstStudyItems = MutableStateFlow(emptyList)
    val firstStudyItems = _firstStudyItems.asStateFlow()

    private val _isVisiblePlusBtn = MutableStateFlow(false)
    val isVisiblePlusBtn = _isVisiblePlusBtn.asStateFlow()

    private val _isVisibleAllHint = MutableStateFlow(false)
    val isVisibleAllHint = _isVisibleAllHint.asStateFlow()

    private val _isVisibleDialog = MutableStateFlow(false)
    val isVisibleDialog = _isVisibleDialog.asStateFlow()

    private val _pageList = MutableStateFlow(listOf(""))
    val pageList = _pageList.asStateFlow()

    private val _userRuleState = MutableStateFlow(false)
    val userRuleState = _userRuleState.asStateFlow()

    private val _originGuideLabel = MutableStateFlow(0)
    val originGuideLabel = _originGuideLabel.asStateFlow()

    private val _firstGuideLabel = MutableStateFlow(0)
    val firstGuideLabel = _firstGuideLabel.asStateFlow()

    private val _blankGuideLabel = MutableStateFlow(true)
    val blankGuideLabel = _blankGuideLabel.asStateFlow()

    private val _connectionState = MutableStateFlow(false)
    val connectionState = _connectionState.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyPage.DYNASTY_TYPE_KEY)
        val studyType = savedStateHandle.get<String>(Destination.StudyPage.STUDY_TYPE_KEY)

        _dynastyType.value = dynastyType ?: throw IllegalArgumentException("Dynasty Type Error")
        _studyType.value = studyType ?: throw IllegalArgumentException("Study Type Error")

        observeNetworkState()
    }

    fun initStudyData() {
        viewModelScope.launch {
            checkedRemoteState(dynastyType.value, STUDY_TYPE_ALL)
            if (studyType.value == StudyType.FIRST_REVIEW.value) {
                checkedRemoteState(dynastyType.value, STUDY_TYPE_FIRST)
            }
            getUserRule(studyType.value)
        }
    }

    private suspend fun checkedRemoteState(dynastyType: String, studyType: String) {
        val scope = viewModelScope.launch {
            studyInfoUseCase.getRemoteUpdateState(dynastyType, studyType)
                .catch { Log.d("Get Remote State", "result: ${it.message}") }
                .collect {
                    _remoteState.value = it
                }
        }
        scope.join()
        checkedGetType(dynastyType, studyType, studyType == STUDY_TYPE_FIRST)
        scope.cancel()
    }

    private suspend fun checkedGetType(
        dynastyType: String,
        studyType: String,
        checkedStudyType: Boolean,
    ) {
        if (remoteState.value) {
            getLocalStudyItems(dynastyType, studyType, checkedStudyType)
        } else {
            getRemoteStudyItems(dynastyType, studyType, checkedStudyType)
        }
    }

    private suspend fun getRemoteStudyItems(
        dynastyType: String,
        studyType: String,
        checkedStudyType: Boolean,
    ) {
        val scope = viewModelScope.launch {
            studyInfoUseCase.getRemoteStudyInfo(dynastyType, studyType)
                .catch { Log.d("Get All Data", "result: ${it.message}") }
                .collect {
                    when (it) {
                        is NetworkResult.Success -> {
                            if (checkedStudyType) _firstStudyItems.value = it.data
                            else _originStudyItems.value = it.data
                            _pageList.value = getPageList(it.data)
                        }
                        is NetworkResult.Error -> {
                            throw NetworkErrorException("Network Error")
                        }
                        else -> {
                            throw IllegalArgumentException("Type Error")
                        }
                    }
                }
        }
        scope.join()
        val insertItems = if (checkedStudyType) firstStudyItems.value else originStudyItems.value
        insertStudyItems(insertItems, dynastyType, studyType)
        updateRemoteState(dynastyType, studyType, true)
        scope.cancel()
    }

    private fun getLocalStudyItems(
        dynastyType: String,
        studyType: String,
        checkedStudyType: Boolean,
    ) {
        viewModelScope.launch {
            studyInfoUseCase
                .getLocalStudyInfo(dynastyType, studyType)
                .catch { Log.d("Get All Data", "result: ${it.message}") }
                .collect {
                    if (checkedStudyType) _firstStudyItems.value = it
                    else _originStudyItems.value = it
                    _pageList.value = getPageList(it)
                }
        }
    }

    private fun insertStudyItems(
        studyInfo: List<StudyInfoItem>,
        dynastyType: String,
        studyType: String,
    ) {
        viewModelScope.launch {
            studyInfoUseCase.insertLocalStudyInfo(studyInfo, dynastyType, studyType)
        }
    }

    private fun updateRemoteState(dynastyType: String, studyType: String, state: Boolean) {
        viewModelScope.launch {
            studyInfoUseCase.setRemoteUpdateState(dynastyType, studyType, state)
        }
    }

    //MyStudy
    fun insertMyStudyItems(studyInfo: List<StudyInfoItem>) {
        viewModelScope.launch {
            studyInfoUseCase.insertMyStudyInfo(studyInfo)
            initPlusButton()
        }
    }

    //Dialog
    fun onOpenDialogClicked() {
        _isVisibleDialog.value = true
    }

    fun onDialogConfirm() {
        insertMyStudyItems(originStudyItems.value)
        _isVisibleDialog.value = false
    }

    fun onDialogDismiss() {
        _isVisibleDialog.value = false
    }

    //Guide
    private fun getUserRule(studyType: String) {
        val guideKey = when (studyType) {
            StudyType.ORIGIN_STUDY.value -> GuideKey.USER_RULE_ORIGIN.value
            StudyType.FIRST_REVIEW.value -> GuideKey.USER_RULE_FIRST.value
            StudyType.ALL_BLANK_REVIEW.value -> GuideKey.USER_RULE_BLANK.value
            else -> throw IllegalArgumentException("Guide Key Error")
        }

        viewModelScope.launch {
            studyInfoUseCase.getGuideState(guideKey)
                .catch { Log.d("Get Gudie Key Error", "result: ${it.message}") }
                .collect { _userRuleState.value = it }
        }
    }

    fun updateUserRule(key: String) {
        viewModelScope.launch {
            studyInfoUseCase.setGuideState(key)
        }
    }

    fun updateLabel(label: Int, studyType: String) {
        when (studyType) {
            StudyType.ORIGIN_STUDY.value -> _originGuideLabel.value = label
            StudyType.FIRST_REVIEW.value -> _firstGuideLabel.value = label
            StudyType.ALL_BLANK_REVIEW.value -> _blankGuideLabel.value = false
        }
    }

    //Pager
    private fun getPageList(studyInfo: List<StudyInfoItem>): List<String> {
        val pagerList = mutableListOf<String>()
        studyInfo.forEach {
            if (pagerList.contains(it.detail).not()) {
                pagerList.add(it.detail)
            }
        }
        return pagerList
    }

    //Button
    private fun initPlusButton() {
        _isVisiblePlusBtn.value = false
    }

    fun checkedButtonState(itemsSize: Int) {
        _isVisiblePlusBtn.value = itemsSize > 0
    }

    //Hint
    fun changeAllHintState() {
        _isVisibleAllHint.value = isVisibleAllHint.value.not()
    }

}