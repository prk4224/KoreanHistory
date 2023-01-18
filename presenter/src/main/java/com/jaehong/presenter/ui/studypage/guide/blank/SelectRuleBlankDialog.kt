package com.jaehong.presenter.ui.studypage.guide.blank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.BlackWithAlpha50
import com.jaehong.presenter.ui.studypage.StudyPageViewModel
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.USER_RULE_BLANK

@Composable
fun SelectRuleBlankDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val checkboxBlankImage = painterResource(id = R.drawable.check_box_blank)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackWithAlpha50)
        .clickable(
            onClick = {
                studyPageViewModel.updateLabel(0, ALL_BLANK_REVIEW)
                studyPageViewModel.setUserRule(USER_RULE_BLANK)
            }
        )
    ) {
        Image(
            painter = checkboxBlankImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .fillMaxWidth()
                .size(165.dp)

        )
    }
}