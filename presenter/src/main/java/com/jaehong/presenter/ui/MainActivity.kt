package com.jaehong.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.presenter.ui.home.HomeScreen
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
import com.jaehong.presenter.util.Constants.GO_LYEO
import com.jaehong.presenter.util.Constants.JO_SEON
import com.jaehong.presenter.util.Constants.MY_KEYWORD
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY
import com.jaehong.presenter.util.Constants.SAM_GUG
import com.jaehong.presenter.util.Constants.SIN_LA
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
        val studyList = listOf(ORIGIN_STUDY, FIRST_REVIEW, ALL_BLANK_REVIEW)
    }
}