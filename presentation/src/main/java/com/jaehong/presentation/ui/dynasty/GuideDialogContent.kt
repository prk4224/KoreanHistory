package com.jaehong.presentation.ui.dynasty

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.R
import com.jaehong.presentation.theme.BlackWithAlpha50
import com.jaehong.presentation.theme.Gray1
import com.jaehong.presentation.theme.Gray3
import com.jaehong.presentation.theme.Typography
import com.jaehong.presentation.util.Constants.GUIDE_CHECKED_TEXT
import com.jaehong.presentation.util.Constants.GUIDE_OUT_TEXT
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun GuideDialogContent(
    guideImage: Painter = painterResource(id = R.drawable.guide_image),
    onGuideClicked: (Boolean) -> Unit,
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(BlackWithAlpha50)
    ) {
        Box(
            modifier = Modifier
                .width(340.dp)
                .height(630.dp)
                .background(Gray1, RoundedCornerShape(10, 10, 0, 0))
        ) {
            Image(
                painter =guideImage,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .align(Alignment.TopCenter)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 60.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { onCheckChange(checked.not()) },
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 20.dp),
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = Color.Black,
                    )
                )
                Text(
                    text = GUIDE_CHECKED_TEXT,
                    fontSize = 20.nonScaledSp,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            Button(
                onClick = { onGuideClicked(checked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Gray3, RectangleShape)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(Gray3)
            ) {
                Text(text = GUIDE_OUT_TEXT,
                    style = Typography.bodyLarge,
                    fontSize = 26.nonScaledSp,
                    color = Color.White
                )
            }
        }
    }
}