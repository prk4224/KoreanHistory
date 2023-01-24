package com.jaehong.presenter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jaehong.domain.local.model.enum_type.*
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
                DynastyType.MODERN.value,
                DynastyType.JAPANESE.value,
                DynastyType.CONTEMPORARY.value,
                DynastyType.MY_KEYWORD.value,
            )

        val studyList =
            listOf(
                StudyType.ORIGIN_STUDY.value,
                StudyType.FIRST_REVIEW.value,
                StudyType.ALL_BLANK_REVIEW.value
            )
        val modernList =
            listOf(
                ModernType.ONE.value,
                ModernType.TWO.value,
                ModernType.THREE.value,
                ModernType.FOUR.value,
            )

        val japaneseList =
            listOf(
                JapaneseType.ONE.value,
                JapaneseType.TWO.value,
                JapaneseType.THREE.value,
            )

        val contemporaryList =
            listOf(
                ContemporaryType.ONE.value,
                ContemporaryType.TWO.value,
                ContemporaryType.THREE.value,
            )
    }
}