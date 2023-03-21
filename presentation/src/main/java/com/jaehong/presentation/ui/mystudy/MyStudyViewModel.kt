package com.jaehong.presentation.ui.mystudy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.model.result.UiStateResult
import com.jaehong.domain.local.usecase.GetMyStudyInfoUseCase
import com.jaehong.presentation.navigation.KoreanHistoryNavigator
import com.jaehong.presentation.util.checkedResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyStudyViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    private val myStudyInfoUseCase: GetMyStudyInfoUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiStateResult.LOADING)
    val uiState = _uiState.asStateFlow()

    private val _myStudyItems = MutableStateFlow(listOf<StudyInfoItem>())
    val myStudyItems = _myStudyItems.asStateFlow()

    private val _isVisibleMinusBtn = MutableStateFlow(false)
    val isVisibleMinusBtn = _isVisibleMinusBtn.asStateFlow()

    private val _pageList = MutableStateFlow(listOf(""))
    val pageList = _pageList.asStateFlow()

    private val _isVisibleDialog = MutableStateFlow(false)
    val isVisibleDialog = _isVisibleDialog.asStateFlow()

    private val _userRuleState = MutableStateFlow(false)
    val userRuleState = _userRuleState.asStateFlow()

    init {
        getMyStudyItems()
        getUserRule()
    }

    private fun getMyStudyItems() {
        viewModelScope.launch {
            myStudyInfoUseCase()
                .catch { Log.d("My Study Data", "result : $it") }
                .collect {
                    checkedResult(
                        dbResult = it,
                        success = { items ->
                            _uiState.value = UiStateResult.SUCCESS
                            _myStudyItems.value = items
                            _pageList.value = getPageList(items)
                        }
                    )
                }
        }
    }

    fun deleteMyStudyItems(selected: List<StudyInfoItem>) {
        viewModelScope.launch {
            _uiState.value = UiStateResult.LOADING
            myStudyInfoUseCase.deleteMyStudyInfo(selected)
                .catch {  Log.d("Insert Data", "result: ${it.message}") }
                .collect {
                    checkedResult(
                        dbResult = it,
                        success = {
                            _uiState.value = UiStateResult.SUCCESS
                            Log.d("Room Result", "MyStudyEntity Delete Ok")
                            initMinusButton()
                            getMyStudyItems()
                        }
                    )
                }
        }
    }

    //Dialog
    fun onOpenDialogClicked() {
        _isVisibleDialog.value = true
    }

    fun onDialogConfirm() {
        deleteMyStudyItems(myStudyItems.value)
        _isVisibleDialog.value = false
    }

    fun onDialogDismiss() {
        _isVisibleDialog.value = false
    }

    //Guide
    private fun getUserRule() {
        viewModelScope.launch {
            myStudyInfoUseCase.getGuideState(GuideKey.USER_RULE_MY_PAGE.value)
                .catch { Log.d("My Page Guide Rule", "result: ${it.message}") }
                .collect { _userRuleState.value = it }
        }
    }

    fun setUserRule(key: String) {
        viewModelScope.launch {
            myStudyInfoUseCase.setGuideState(key)
            _userRuleState.value = false
        }
    }

    //Pager
    private fun getPageList(myStudyList: List<StudyInfoItem>): List<String> {
        val pageList = mutableListOf<String>()
        myStudyList.forEach {
            if (pageList.contains(it.detail).not()) {
                pageList.add(it.detail)
            }
        }
        return pageList
    }

    //Button
    fun checkedButtonState(itemSize: Int) {
        _isVisibleMinusBtn.value = itemSize > 0
    }

    private fun initMinusButton() {
        _isVisibleMinusBtn.value = false
    }

    fun onBackButtonClicked() {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateBack()
        }
    }
}