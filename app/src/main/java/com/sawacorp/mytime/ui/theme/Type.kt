package com.sawacorp.mytime.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sawacorp.mytime.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontSize = 18.sp,
        color = Black80,
        lineHeight = 21.sp,
        fontFamily = FontFamily(Font(R.font.gotham)),
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight(500),
        letterSpacing = 0.1.sp
    )
/*
Other default text styles to override
    labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
)

val mainStyle = TextStyle(
    fontSize = 18.sp,
    color = Black80,
    lineHeight = 21.sp,
    fontFamily = FontFamily(Font(R.font.gotham)),
    textAlign = TextAlign.Center,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(500),
    letterSpacing = 0.1.sp
)

val itemStyleText = TextStyle(
    fontSize = 16.sp,
    color = Black90,
    lineHeight = 19.sp,
    fontFamily = FontFamily(Font(R.font.gotham_medium)),
    textAlign = TextAlign.Center,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(500),
    letterSpacing = 0.1.sp
)

val itemStyleTime = TextStyle(
    fontSize = 13.sp,
    color = Grey80,
    lineHeight = 15.sp,
    fontFamily = FontFamily(Font(R.font.gotham_medium)),
    textAlign = TextAlign.Center,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(500),
    letterSpacing = 0.1.sp
)

val itemStylePeriod = TextStyle(
    fontSize = 16.sp,
    color = Grey90,
    lineHeight = 24.sp,
    fontFamily = FontFamily(Font(R.font.gotham_medium)),
    textAlign = TextAlign.Center,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(400),
    letterSpacing = 0.5.sp
)