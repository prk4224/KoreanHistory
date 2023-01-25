package com.jaehong.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.domain.local.model.enum_type.*
import com.jaehong.presenter.ui.home.HomeScreen
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
                ModernType.ONE,
                ModernType.TWO,
                ModernType.THREE,
                ModernType.FOUR,
            )

        val japaneseList =
            listOf(
                JapaneseType.ONE,
                JapaneseType.TWO,
                JapaneseType.THREE,
            )

        val contemporaryList =
            listOf(
                ContemporaryType.ONE,
                ContemporaryType.TWO,
                ContemporaryType.THREE,
            )
    }
}