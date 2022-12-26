package com.jaehong.koreanhistory.presenter.home

import androidx.lifecycle.ViewModel
import com.jaehong.koreanhistory.navigation.KoreanHistoryNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    koreanHistoryNavigator: KoreanHistoryNavigator
) : ViewModel() {
    val navigationChannel = koreanHistoryNavigator.navigationChannel
}