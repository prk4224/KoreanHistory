package com.jaehong.presentation.ui.studypage.item

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import com.jaehong.presentation.theme.BaseColor1
import com.jaehong.presentation.util.Extension.checkedType
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StudyPageHeaderItem(
    dynastyState:String,
    pageTitle: String,
    //updateState: Boolean,
    header: String = if(dynastyState.checkedType()) pageTitle else "$dynastyState, $pageTitle",
    //onLongClick: () -> Unit,
    //addItem: () -> Unit
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BaseColor1, RoundedCornerShape(50, 50, 0, 0))
        .combinedClickable(
            onClick = { },
            onLongClick = {
                //onLongClick()
            }
        ),
    ){
        Text(
            text = header,
            textAlign = TextAlign.Center,
            fontSize = 30.nonScaledSp,
            color = Color.White,
            lineHeight = 30.nonScaledSp,
            modifier = Modifier
                .padding(vertical = 7.dp)
                .align(Alignment.Center)
        )
//        if(updateState) {
//            Text(
//                text = "추가",
//                textAlign = TextAlign.Center,
//                fontSize = 18.nonScaledSp,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(7.dp)
//                    .align(Alignment.CenterEnd)
//                    .clickable { addItem() }
//            )
//        }
    }
}