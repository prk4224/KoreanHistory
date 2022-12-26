package com.jaehong.koreanhistory.presenter.dynasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.koreanhistory.presenter.MainActivity
import com.jaehong.koreanhistory.ui.theme.DynastyButtonColor
import com.jaehong.koreanhistory.ui.theme.KoreanHistoryTheme
import com.jaehong.koreanhistory.ui.theme.Typography

@Composable
fun DynastyScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MainActivity.dynastyList.forEach {
                DynastyButton(title = it)
            }
        }
    }
}

@Composable
fun DynastyButton(
    title: String,
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    Button(
        onClick = {
            dynastyViewModel.onNavigateToStudyTypeClicked(title)
        },
        Modifier
            .width(250.dp)
            .background(Color.White)
            .padding(15.dp),
        colors = ButtonDefaults.buttonColors(DynastyButtonColor)
    ) {
        Text(text = title, style = Typography.bodyLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun DynastyScreenPreview() {
    KoreanHistoryTheme {
        DynastyScreen()
    }
}