package com.jaehong.presentation.ui.mystudy.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.enum_type.DynastyType
import com.jaehong.presentation.theme.MyStudyColor
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun MyStudyHeaderItem(
    title: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(MyStudyColor, RoundedCornerShape(50, 50, 0, 0))
    ){
        Text(
            text = DynastyType.MY_KEYWORD.value,
            fontSize = 35.nonScaledSp,
            color = Color.White,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(
            text ="($title)",
            fontSize = 24.nonScaledSp,
            color = Color.White,
        )
    }
}