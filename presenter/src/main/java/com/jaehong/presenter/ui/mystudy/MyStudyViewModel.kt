package com.jaehong.presenter.ui.mystudy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.usecase.GetMyStudyInfoUseCase
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
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

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    init {
        getMyStudyData()
    }

    private fun getMyStudyData(){
        viewModelScope.launch {
            myStudyInfoUseCase()
                .catch { Log.d("My Study Data","result : $it") }
                .collect {
                    _myStudyInfoList.value = it
                    _pagerList.value = getPagerList(it)
                }
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

    fun updatePage(page: Int) {
        _currentPage.value = page
        clearSelectedItems()
    }

    fun changeSelectedItem(studyInfoItem: StudyInfoItem, check: Boolean) {
        if (check) _selectedItems.value.add(studyInfoItem)
        else _selectedItems.value.remove(studyInfoItem)
    }

    fun getSelectedItemsSize(): Int {
        return selectedItems.value.size
    }

    fun changeButtonState(itemSize: Int) {
        _isVisible.value = itemSize > 0
    }

    fun deleteMyStudyInfo(selected: List<StudyInfoItem>) {
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

    private fun deleteAllData(myStudyItems: List<StudyInfoItem>) {
        viewModelScope.launch {
            myStudyInfoUseCase.deleteMyStudyInfo(myStudyItems)
            getMyStudyData()
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