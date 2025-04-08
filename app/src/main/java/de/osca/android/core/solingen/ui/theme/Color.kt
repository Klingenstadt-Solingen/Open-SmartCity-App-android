package de.osca.android.core.solingen.ui.theme

import androidx.compose.ui.graphics.Color
import de.osca.android.core.solingen.isDarkTheme

// STYLEGUIDE
val GUIDE_Primary = Color(0xFF005AAA)
val GUIDE_OnPrimary = Color(0xFFFFFFFF)
val GUIDE_Secondary = Color(0xFFBFD6EA)
val GUIDE_OnSecondary = Color(0xFF000000)
val GUIDE_Surface = Color(0xFFFFFFFF)
val GUIDE_OnSurface = Color(0xFF000000)
val GUIDE_CitySpecific = Color(0xFFFFD503)
val GUIDE_Error = Color(0xFFB4292A)
val GUIDE_Warning = Color(0xFFF3BB1C)
val GUIDE_Success = Color(0xFF3CC13B)
val GUIDE_Hint = Color(0xFFA1A1A1)
val GUIDE_DarkWhite = Color(0xFFC2C2C2)
val GUIDE_Black = Color(0xFF000000)

// MY PROPERTIES
fun CLR_TextNormal(): Color =  if (isDarkTheme) GUIDE_OnSurface else GUIDE_OnSurface
fun CLR_TextMain(): Color = if (isDarkTheme) GUIDE_OnSurface else GUIDE_OnSurface
fun CLR_TextCard(): Color  = if (isDarkTheme) GUIDE_OnSurface else GUIDE_OnSurface
fun CLR_TextSheet(): Color  = if (isDarkTheme) GUIDE_OnSurface else GUIDE_OnSurface
fun CLR_TextScreen(): Color  = if (isDarkTheme) GUIDE_OnSurface else GUIDE_OnSurface
fun CLR_TextHint(): Color  = if (isDarkTheme) GUIDE_Hint else GUIDE_Hint
fun CLR_TextButton(): Color  = if (isDarkTheme) GUIDE_OnPrimary else GUIDE_OnPrimary
fun CLR_TextDialog(): Color  = if (isDarkTheme) GUIDE_OnPrimary else GUIDE_OnPrimary
fun CLR_BackgroundMain(): Color  = if (isDarkTheme) GUIDE_Surface else GUIDE_Surface
fun CLR_BackgroundCard(): Color  = if (isDarkTheme) GUIDE_Surface else GUIDE_Surface
fun CLR_BackgroundSheet(): Color  = if (isDarkTheme) GUIDE_Surface else GUIDE_Surface
fun CLR_BackgroundDialog(): Color  = if (isDarkTheme) GUIDE_Primary else GUIDE_Primary
fun CLR_BackgroundButton(): Color  = if (isDarkTheme) GUIDE_Primary else GUIDE_Primary
fun CLR_Accent(): Color  = if (isDarkTheme) GUIDE_Primary else GUIDE_Primary
fun CLR_ShowMore(): Color  = if (isDarkTheme) GUIDE_Primary else GUIDE_Primary
fun CLR_ErrorText(): Color  = if (isDarkTheme) GUIDE_Error else GUIDE_Error
fun CLR_WaringText(): Color  = if (isDarkTheme) GUIDE_Warning else GUIDE_Warning
fun CLR_SuccessText(): Color  = if (isDarkTheme) GUIDE_Success else GUIDE_Success
fun CLR_City(): Color  = if (isDarkTheme) GUIDE_CitySpecific else GUIDE_CitySpecific
fun CLR_GradientTop(): Color  = if (isDarkTheme) GUIDE_Primary else GUIDE_Primary
fun CLR_GradientBottom(): Color  = if (isDarkTheme) GUIDE_Secondary else GUIDE_Secondary