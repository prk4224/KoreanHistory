package com.jaehong.presenter.ui.studypage.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.ui.studypage.StudyPageViewModel
import com.jaehong.presenter.util.Constants
import com.jaehong.presenter.util.Constants.USER_RULE_ALL

@Composable
fun SelectRuleDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val checkboxAllImage = painterResource(id = R.drawable.checkbox_all_image)
    val guidePlusImage = painterResource(id = R.drawable.guide_plus_image)

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                studyPageViewModel.updateLabel(3)
                studyPageViewModel.setUserRule(USER_RULE_ALL)
            }
        )
    ) {
        Image(
            painter = checkboxAllImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
                .fillMaxWidth()
                .height(150.dp)
                .size(250.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Image(
                painter = guidePlusImage,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .size(200.dp)
            )

            Icon(
                imageVector = Icons.Filled.AddCircle,
                tint = BaseColor1,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.End)
                    .padding(bottom = 20.dp, end = 20.dp)
                    .background(Color.White, CircleShape)

            )
        }
    }
}