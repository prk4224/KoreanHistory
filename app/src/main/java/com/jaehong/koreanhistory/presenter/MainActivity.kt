package com.jaehong.koreanhistory.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaehong.koreanhistory.R
import com.jaehong.koreanhistory.presenter.home.HomeScreen
import com.jaehong.koreanhistory.util.DynastyConstants.GO_LYEO
import com.jaehong.koreanhistory.util.DynastyConstants.JO_SEON
import com.jaehong.koreanhistory.util.DynastyConstants.MY_KEYWORD
import com.jaehong.koreanhistory.util.DynastyConstants.SAM_GUG
import com.jaehong.koreanhistory.util.DynastyConstants.SIN_LA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }

    companion object {
        val dynastyList = listOf(SAM_GUG, SIN_LA, GO_LYEO, JO_SEON, MY_KEYWORD)
    }
}


@Composable
fun SplashScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {

        Text(text = "즐거운 역사 공부",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 100.dp),
            fontSize = 30.sp,
            fontFamily = FontFamily.Default
            )

        Image(
            painter = painterResource(id = R.drawable.koreanhistory_splash_screen),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.BottomCenter
        )

    }
}
