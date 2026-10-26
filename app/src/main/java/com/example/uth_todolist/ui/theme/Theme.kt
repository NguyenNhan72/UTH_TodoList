package com.example.uth_todolist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Khai báo bảng màu tối
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF3558F9),
    secondary = Color(0xFF757575),
    tertiary = Color(0xFFE0E0E0)
)

// Khai báo bảng màu sáng
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF3558F9),
    secondary = Color(0xFF757575),
    tertiary = Color(0xFFE0E0E0)
)

// Hàm Theme chính của ứng dụng
@Composable
fun UTH_TodoListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}