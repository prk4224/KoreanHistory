package com.jaehong.koreanhistory.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.koreanhistory.presenter.home.HomeScreen
import com.jaehong.koreanhistory.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.koreanhistory.util.Constants.BLANK_REVIEW
import com.jaehong.koreanhistory.util.Constants.GO_LYEO
import com.jaehong.koreanhistory.util.Constants.JO_SEON
import com.jaehong.koreanhistory.util.Constants.MY_KEYWORD
import com.jaehong.koreanhistory.util.Constants.ORIGIN_STUDY
import com.jaehong.koreanhistory.util.Constants.SAM_GUG
import com.jaehong.koreanhistory.util.Constants.SIN_LA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    companion object {
        val dynastyList = listOf(SAM_GUG, SIN_LA, GO_LYEO, JO_SEON, MY_KEYWORD)
        val studyList = listOf(ORIGIN_STUDY, BLANK_REVIEW, ALL_BLANK_REVIEW)
    }
}