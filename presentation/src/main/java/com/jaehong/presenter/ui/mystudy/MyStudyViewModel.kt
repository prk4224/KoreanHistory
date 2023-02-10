package com.jaehong.presenter.ui.mystudy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.usecase.GetMyStudyInfoUseCase
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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

    private val _pagerList = MutableStateFlow(listOf(""))
    val pagerList = _pagerList.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _checkedUserRule = MutableStateFlow(false)
    val checkedUserRule = _checkedUserRule.asStateFlow()

    init {
        getMyStudyData()
    }

    private fun getMyStudyData(){
        viewModelScope.launch {
            with(myStudyInfoUseCase) {
                launch {
                    this@with()
                        .catch { Log.d("My Study Data","result : $it") }
                        .collect {
                            _myStudyInfoList.value = it
                            _pagerList.value = getPagerList(it)
                        }
                }
                launch {
                    getGuideInfo(GuideKey.USER_RULE_MY_PAGE.value)
                        .catch { Log.d("My Page Guide Rule", "result: ${it.message}") }
                        .collect { _checkedUserRule.value = it }
                }
            }
        }
    }

    fun setUserRule(key: String) {
        viewModelScope.launch {
            myStudyInfoUseCase.setGuideInfo(key)
            _checkedUserRule.value = false
        }
    }

    fun onOpenDialogClicked() {
        _showDialog.value = true
    }

    fun onDialogConfirm(myStudyItems: List<StudyInfoItem>) {
        deleteAllData(myStudyItems)
        _showDialog.value = false
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun changeButtonState(itemSize: Int) {
        _isVisible.value = itemSize > 0
    }

    fun deleteMyStudyInfo(selected: List<StudyInfoItem>) {
        viewModelScope.launch {
            myStudyInfoUseCase.deleteMyStudyInfo(selected)
            initDataChangeButton()
            getMyStudyData()
        }
    }

    private fun initDataChangeButton() {
        _isVisible.value = false
    }

    fun onBackButtonClicked() {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateBack()
        }
    }

    private fun deleteAllData(myStudyItems: List<StudyInfoItem>) {
        viewModelScope.launch {
            myStudyInfoUseCase.deleteMyStudyInfo(myStudyItems)
            initDataChangeButton()
            getMyStudyData()
        }
    }

    private fun getPagerList(myStudyList: List<StudyInfoItem>): List<String> {
        val pagerList = mutableListOf<String>()
        myStudyList.forEach {
            if (pagerList.contains(it.detail).not()) {
                pagerList.add(it.detail)
            }
        }
        return pagerList
    }
}