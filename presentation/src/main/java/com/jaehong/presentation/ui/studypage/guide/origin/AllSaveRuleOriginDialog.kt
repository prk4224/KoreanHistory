package com.jaehong.presentation.ui.studypage.guide.origin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.presentation.R

@Composable
fun AllSaveRuleOriginDialog(
    allSaveImage: Painter = painterResource(id = R.drawable.all_save_image),
    onClickUpdate: (Int,String,String) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(4, StudyType.ORIGIN_STUDY.value, GuideKey.USER_RULE_ORIGIN.value)
            }
        )
    ) {
        Image(
            painter = allSaveImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 5.dp, end = 5.dp)
                .size(200.dp)
        )
    }
}