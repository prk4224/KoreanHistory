package com.jaehong.koreanhistory.presenter.studytype

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jaehong.koreanhistory.ui.theme.KoreanHistoryTheme

@Composable
fun StudyTypeScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {

    }
}

@Preview(showBackground = true)
@Composable
fun StudyTypeScreenPreview() {
    KoreanHistoryTheme {
        StudyTypeScreen()
    }
}