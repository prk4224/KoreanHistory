package com.jaehong.presenter.ui.dynasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.BaseColor1

@Composable
fun GuideDialogContent(){

    Column(
        modifier = Modifier
            .padding(vertical = 100.dp, horizontal = 50.dp)
    ) {
        Box(modifier = Modifier
            .background(BaseColor1, RoundedCornerShape(50, 50, 0, 0))
            .fillMaxWidth()
            .weight(1f),
            contentAlignment = Alignment.Center
        ){
            Text(text = "앱 사용 설명서")
        }
        Box(modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .weight(10f),
            contentAlignment = Alignment.Center
        ){
            Text("사진")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = false, onCheckedChange = {})
                Text(text = "다시 보시 않기")
            }
        }
        
        Row(modifier = Modifier
            .fillMaxWidth()
            .weight(1.5f)
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White)
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(BaseColor1),
                shape = RectangleShape
            ) {
                Text(text = "확인")
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                colors = ButtonDefaults.buttonColors(BaseColor1),
                shape = RectangleShape
            ) {
                Text(text = "닫기")
            }
        }
    }
}

@Composable
@Preview
fun DefaultPreView(){
    Surface(modifier = Modifier.fillMaxSize()) {
        GuideDialogContent()
    }
}