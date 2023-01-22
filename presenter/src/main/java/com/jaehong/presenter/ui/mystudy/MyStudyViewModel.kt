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

    private val _pagerList = MutableStateFlow(listOf(""))
    val pagerList = _pagerList.asStateFlow()

    private val _currentPage = MutableStateFlow(0)
    val currentPage = _currentPage.asStateFlow()

    init {
        getMyStudyData()
    }

    private fun getMyStudyData(){
        viewModelScope.launch {
            val myStudyList = myStudyInfoUseCase()
            _myStudyInfoList.value = myStudyList
            _pagerList.value = getPagerList(myStudyList)
        }
    }

    fun updatePage(page: Int) {
        _currentPage.value = page
        clearSelectedItems()
    }

    fun changeSelectedItem(studyInfoItem: StudyInfoItem, check: Boolean) {
        if (check) _selectedItems.value.add(studyInfoItem)
        else _selectedItems.value.remove(studyInfoItem)
    }

    fun changeButtonState() {
        _isVisible.value = selectedItems.value.size > 0
    }

    fun deleteMyStudyInfo(selected: MutableList<StudyInfoItem>) {
        viewModelScope.launch {
            myStudyInfoUseCase.deleteMyStudyInfo(selected)
            getMyStudyData()
            clearSelectedItems()
        }
    }

    fun onBackButtonClicked() {
        viewModelScope.launch {
            koreanHistoryNavigator.navigateBack()
        }
    }

    private fun clearSelectedItems(){
        _selectedItems.value.clear()
        _isVisible.value = false
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