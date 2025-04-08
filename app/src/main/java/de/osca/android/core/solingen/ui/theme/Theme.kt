package de.osca.android.core.solingen.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val LightColorPalette = lightColors(
    primary = CLR_Accent()
)
val DarkColorPalette = darkColors(
    primary = CLR_Accent()
)

@Composable
fun OSCACoreTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if(isSystemInDarkTheme()) DarkColorPalette else LightColorPalette,
        typography = Typography,
        content = content
    )
}