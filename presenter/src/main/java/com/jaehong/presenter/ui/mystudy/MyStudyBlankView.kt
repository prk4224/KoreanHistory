package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.Gray3
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.util.Constants.PLUS_KEYWORD_TEXT
import com.jaehong.presenter.util.Constants.REVIEW_TEXT

@Composable
fun MyStudyBlankView(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val blankImage = painterResource(id = R.drawable.black_image)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray3),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = blankImage,
                contentDescription = "Blank Image",
                modifier = Modifier.size(230.dp)
            )

            Text(
                text = REVIEW_TEXT,
                fontSize = 26.sp,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(10.dp),
            )

            Button(
                onClick = { myStudyViewModel.onBackButtonClicked() },
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = PLUS_KEYWORD_TEXT,
                    style = Typography.bodyLarge,
                    color = Color.Black
                )
            }
        }
    }
}