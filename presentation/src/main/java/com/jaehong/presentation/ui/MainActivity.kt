package com.jaehong.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.domain.local.model.enum_type.*
import com.jaehong.presentation.ui.home.HomeScreen
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
                DynastyType.SAM_GUG,
                DynastyType.SIN_LA,
                DynastyType.GO_LYEO,
                DynastyType.JO_SEON,
                DynastyType.MODERN,
                DynastyType.JAPANESE,
                DynastyType.CONTEMPORARY,
                DynastyType.MY_KEYWORD,
            )

        val studyList =
            listOf(
                StudyType.ORIGIN_STUDY,
                StudyType.FIRST_REVIEW,
                StudyType.ALL_BLANK_REVIEW
            )
        val modernList =
            listOf(
                DynastyType.MODERN.detail(1),
                DynastyType.MODERN.detail(2),
                DynastyType.MODERN.detail(3),
                DynastyType.MODERN.detail(4),
            )

        val japaneseList =
            listOf(
                DynastyType.JAPANESE.detail(1),
                DynastyType.JAPANESE.detail(2),
                DynastyType.JAPANESE.detail(3),
            )

        val contemporaryList =
            listOf(
                DynastyType.CONTEMPORARY.detail(1),
                DynastyType.CONTEMPORARY.detail(2),
                DynastyType.CONTEMPORARY.detail(3),
            )
    }
}