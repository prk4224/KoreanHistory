package com.jaehong.presentation.ui.studypage.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presentation.theme.WooSuFont
import com.jaehong.presentation.util.Constants.NOTHING
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun StudyAllViewItem(
    studyInfo: StudyInfoItem,
//    updateState: Boolean,
    //list: List<String>,
//    kingName: String,
//    setKingName: (String) -> Unit,
//    addDescription: () -> Unit,
    descriptionView: @Composable (Int, String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .background(Color.White),
    ) {
       // if (studyInfo.king_name != NOTHING || updateState) {
        if (studyInfo.king_name != NOTHING) {
            Box(
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RectangleShape)
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(Color.White)
                    .wrapContentSize(Alignment.Center),
            ) {

                Text(
//                    value = kingName,
//                    onValueChange = { setKingName(it) },
                    //readOnly = updateState.not(),
                    text = studyInfo.king_name,
                    modifier = Modifier.align(Alignment.Center),
                    //textStyle = TextStyle(
                        fontSize = 25.nonScaledSp,
                        fontFamily = WooSuFont,
                        lineHeight = 25.nonScaledSp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                    //)
                )
//                BasicTextField(
//                    value = kingName,
//                    onValueChange = { setKingName(it) },
//                    readOnly = updateState.not(),
//                    modifier = Modifier.align(Alignment.Center),
//                    textStyle = TextStyle(
//                        fontSize = 25.nonScaledSp,
//                        fontFamily = WooSuFont,
//                        lineHeight = 25.nonScaledSp,
//                        color = Color.Black,
//                        textAlign = TextAlign.Center,
//                    )
//                )

//                if (updateState) {
//                    Text(
//                        text = "추가",
//                        modifier = Modifier
//                            .border(1.dp, Color.LightGray, RectangleShape)
//                            .align(Alignment.CenterEnd)
//                            .background(Color.LightGray)
//                            .clickable { addDescription() },
//                    )
//                }
            }
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .border(1.dp, Color.LightGray, RectangleShape)
                .fillMaxHeight(),
        ) {
            studyInfo.description.forEachIndexed { descIndex, description ->
                descriptionView(descIndex, description)
            }
        }
    }
}
