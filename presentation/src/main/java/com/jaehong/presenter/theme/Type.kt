package com.jaehong.presenter.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jaehong.presentation.R

val WooSuFont = FontFamily(
    Font(R.font.woo_su)
)



val GmarketBoldFont = FontFamily(
    Font(R.font.gmarket_sans_bold)
)

val GmarketLightFont = FontFamily(
    Font(R.font.gmarket_sans_light)
)

val GmarketMediumFont = FontFamily(
    Font(R.font.gmarket_sans_medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = WooSuFont,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = WooSuFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val GmarketBoldText = TextStyle(
    fontFamily = GmarketBoldFont,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

val GmarketLightText = TextStyle(
    fontFamily = GmarketLightFont,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

val GmarketMediumText = TextStyle(
    fontFamily = GmarketMediumFont,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)