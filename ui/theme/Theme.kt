package com.example.careercheer.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.careercheer.data.ApplicationStatus

val Orange = Color(0xFFFFCC80)
val Blue = Color(0xFF90CAF9)
val Pink = Color(0xFFEF9A9A)
val Green = Color(0xFFA5D6A7)
val Yellow = Color(0xFFFFEB3B)
val Red = Color(0xFFFF4763)
val Purple = Color(0xFFCE93D8)
val Teal = Color(0xFF79BAEC)
val LightGray = Color(0xFFBDBDBD)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    secondary = Orange,
    surface = Color.White,
    background = Color(0xFFFAFAFA),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = Blue,
    secondary = Orange,
    surface = Color.White,
    background = Color(0xFFFAFAFA),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun CareerCheerTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}

val StatusColorMap = mapOf(
    ApplicationStatus.Applied to Pink,
    ApplicationStatus.BehavioralInterview to Purple,
    ApplicationStatus.TechnicalInterview to Purple,
    ApplicationStatus.TakeHomeAssessment to Purple,
    ApplicationStatus.Onsite to Orange,
    ApplicationStatus.Whiteboard to Yellow,
    ApplicationStatus.Offer to Teal,
    ApplicationStatus.Accepted to Green,
    ApplicationStatus.Rejected to Red,
    ApplicationStatus.PositionFilled to LightGray
)
