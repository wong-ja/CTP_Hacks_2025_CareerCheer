package com.example.careercheer.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.careercheer.data.ApplicationStatus

val Orange = Color(0xFFFF9800)
val Blue = Color(0xFF2196F3)
val Pink = Color(0xFFE91E63)
val Green = Color(0xFF4CAF50)
val Yellow = Color(0xFFFFEB3B)
val Red = Color(0xFFF44336)
val Purple = Color(0xFF9C27B0)
val Teal = Color(0xFF009688)
val LightGray = Color(0xFFBDBDBD)

private val LightColorScheme = lightColorScheme(
    primary = Orange,
    secondary = Blue,
    surface = Color.White,
    background = Color(0xFFFDF6E3),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange,
    secondary = Blue,
    surface = Color.Black,
    background = Color(0xFF121212),
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
    ApplicationStatus.Applied to Blue,
    ApplicationStatus.BehavioralInterview to Teal,
    ApplicationStatus.TechnicalInterview to Purple,
    ApplicationStatus.TakeHomeAssessment to Yellow,
    ApplicationStatus.Onsite to Pink,
    ApplicationStatus.Whiteboard to Orange,
    ApplicationStatus.Offer to Green,
    ApplicationStatus.Accepted to Green,
    ApplicationStatus.Rejected to Red,
    ApplicationStatus.PositionFilled to LightGray
)
