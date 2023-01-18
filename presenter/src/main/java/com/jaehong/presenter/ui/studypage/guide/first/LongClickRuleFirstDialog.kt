package com.jaehong.presenter.ui.studypage.guide.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.ui.studypage.StudyPageViewModel
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
import com.jaehong.presenter.util.Constants.USER_RULE_FIRST

@Composable
fun LongClickRuleFirstDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val longClickImage = painterResource(id = R.drawable.long_click_image)

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                studyPageViewModel.updateLabel(2, FIRST_REVIEW)
                studyPageViewModel.setUserRule(USER_RULE_FIRST)
            }
        ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = longClickImage,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
    }

}