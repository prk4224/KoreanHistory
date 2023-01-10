package com.jaehong.presenter.ui.mystudy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.usecase.GetMyStudyInfoUseCase
import com.jaehong.presenter.navigation.Destination
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

    fun updatePage(page: Int){
        _currentPage.value = page
        _selectedItems.value.clear()
        _isVisible.value = false
    }

    private fun getPagerList(): List<String> {
        val pagerList = mutableListOf<String>()
        _myStudyInfoList.value.forEach {
            if(pagerList.contains(it.detail).not()){
                pagerList.add(it.detail)
            }
        }
        return pagerList
    }

    private fun getMyStudyData() {
        viewModelScope.launch {
            _myStudyInfoList.value = myStudyInfoUseCase()
            _pagerList.value = getPagerList()
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

    fun deleteMyStudyInfo(studyList: List<StudyInfoItem>){
        viewModelScope.launch {
            myStudyInfoUseCase.deleteMyStudyInfo(studyList)
            onNavigateReFresh()
        }
    }

    private fun onNavigateReFresh() {
        koreanHistoryNavigator.tryNavigateBack()
        koreanHistoryNavigator.tryNavigateTo(Destination.MyStudy())
    }
}