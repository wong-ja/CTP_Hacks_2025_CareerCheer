package com.example.careercheer.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.careercheer.data.ApplicationStatus
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors

val Orange = Color(0xFFEFA43C)
val Blue = Color(0xFF72B8F1)
val Pink = Color(0xFFEF78CE)
val Green = Color(0xFF86DC8A)
val Yellow = Color(0xFFE8D205)
val Red = Color(0xFFFF4763)
val Purple = Color(0xFFB789D9)
val Teal = Color(0xFF79BAEC)
val LightGray = Color(0xFF9F9E9E)

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
        typography = Typography,
        content = content
    )
}

@Composable
fun applicationFieldColors(): TextFieldColors =
    OutlinedTextFieldDefaults.colors(
        focusedTextColor = MaterialTheme.colorScheme.onBackground,
        unfocusedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        disabledTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
        disabledBorderColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        errorBorderColor = MaterialTheme.colorScheme.error,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        disabledLabelColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
        errorLabelColor = MaterialTheme.colorScheme.error
    )

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
