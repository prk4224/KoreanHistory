package com.jaehong.presenter.ui.studypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jaehong.data.datasource.StudyPageDataSource
import com.jaehong.data.model.StudyInfo
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class StudyPageViewModel @Inject constructor(
    private val koreanHistoryNavigator: KoreanHistoryNavigator,
    savedStateHandle: SavedStateHandle,
    dataSource: StudyPageDataSource
) : ViewModel() {

    private val _dynastyState = MutableStateFlow("")
    val dynastyState = _dynastyState.asStateFlow()

    private val _studyState = MutableStateFlow("")
    val studyState = _studyState.asStateFlow()

    private val _studyInfoList = MutableStateFlow(StudyInfo())
    val studyInfoList = _studyInfoList.asStateFlow()

    init {
        val dynastyType = savedStateHandle.get<String>(Destination.StudyPage.DYNASTY_TYPE_KEY)
        val studyType = savedStateHandle.get<String>(Destination.StudyPage.STUDY_TYPE_KEY)

        _dynastyState.value = dynastyType ?: throw IllegalArgumentException("Dynasty Type Error")
        _studyState.value = studyType ?: throw IllegalArgumentException("Study Type Error")
        _studyInfoList.value = dataSource.getStudyInfo()
    }
    fun onBackButtonClicked() {
        koreanHistoryNavigator.tryNavigateBack()
    }
}