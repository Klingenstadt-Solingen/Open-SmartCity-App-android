package de.osca.android.core.solingen.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    // REALLY BIG H1
    h1 = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp,
        color = Color.Black
    ),
    // BIG H2
    h2 = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        color = Color.Black
    ),
    // CAPTION
    caption = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        color = Color.Black
    ),
    // OVERLINE
    overline = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        color = Color.Black
    ),
    // BUTTON
    button = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Black
    ),
    // BODY 1
    body1 = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black
    ),
    // BODY 2
    body2 = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = Color.Black
    ),
    // SUBTITLE 1
    subtitle1 = TextStyle(
        fontFamily = SFPro,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        color = Color.Black
    )
)