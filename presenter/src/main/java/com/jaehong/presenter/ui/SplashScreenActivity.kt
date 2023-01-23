package com.jaehong.presenter.ui

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.util.FontFixed.nonScaledSp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            setContent {
                SplashScreen()
            }
            delay(1000)
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun SplashScreen(
    splashImage: Painter = painterResource(id = R.drawable.koreanhistory_splash_screen)
) {
    Surface(modifier = Modifier.fillMaxSize()) {

        Text(text = "즐거운 역사 공부",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 100.dp),
            fontSize = 30.nonScaledSp,
            style = Typography.bodyLarge
        )

        Image(
            painter = splashImage,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            alignment = Alignment.BottomCenter
        )

    }
}