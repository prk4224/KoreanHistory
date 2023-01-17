package com.jaehong.presenter.ui.studypage.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.ui.studypage.StudyPageViewModel

@Composable
fun SwipeRuleDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
){

    val swipeImage = painterResource(id = R.drawable.swipe_image)

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                studyPageViewModel.updateLabel(1)
            }
        )
    ) {
        Image(
            painter = swipeImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(200.dp)
        )
    }
}

@Composable
@Preview
fun DefaultPreView() {
    SwipeRuleDialog()
}