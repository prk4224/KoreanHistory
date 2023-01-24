package com.jaehong.presenter.ui.studypage.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.util.FontFixed.nonScaledSp
import com.jaehong.domain.local.model.enum_type.DynastyType

@Composable
fun StudyPageHeaderItem(
    dynastyState:String,
    pageTitle: String,
    headerText: String = if(checkedType(dynastyState)) dynastyState else "$dynastyState, $pageTitle"
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BaseColor1, RoundedCornerShape(50, 50, 0, 0)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = headerText,
            textAlign = TextAlign.Center,
            fontSize = 30.nonScaledSp,
            color = Color.White,
            lineHeight = 30.nonScaledSp,
            modifier = Modifier
                .padding(vertical = 7.dp)
        )
    }
}

private fun checkedType(type: String): Boolean {
    return type.contains(DynastyType.MODERN.value)  ||
            type.contains(DynastyType.JAPANESE.value) ||
            type.contains(DynastyType.CONTEMPORARY.value)
}