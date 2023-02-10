package com.jaehong.presenter.ui.studypage.guide.origin

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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.BaseColor1

@Composable
fun SelectRuleOriginDialog(
    checkboxAllImage: Painter = painterResource(id = R.drawable.checkbox_image),
    guidePlusImage: Painter = painterResource(id = R.drawable.guide_plus_image),
    onClickUpdate: (Int,String) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(3, StudyType.ORIGIN_STUDY.value)
            }
        )
    ) {
        Image(
            painter = checkboxAllImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .fillMaxWidth()
                .size(165.dp)

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
                    .padding(bottom = 10.dp,end = 20.dp)
            )

            Icon(
                imageVector = Icons.Filled.AddCircle,
                tint = BaseColor1,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.End)
                    .padding(bottom = 40.dp, end = 40.dp)
                    .background(Color.White, CircleShape)

            )
        }
    }
}
