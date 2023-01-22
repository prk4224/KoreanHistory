package com.jaehong.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.presenter.ui.home.HomeScreen
import com.jaehong.presenter.util.enum.DynastyType
import com.jaehong.presenter.util.enum.StudyType
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
        val dynastyList =
            listOf(
                DynastyType.SAM_GUG.value,
                DynastyType.SIN_LA.value,
                DynastyType.GO_LYEO.value,
                DynastyType.JO_SEON.value,
                DynastyType.MY_KEYWORD.value
            )

        val studyList =
            listOf(
                StudyType.ORIGIN_STUDY.value,
                StudyType.FIRST_REVIEW.value,
                StudyType.ALL_BLANK_REVIEW.value
            )
    }
}