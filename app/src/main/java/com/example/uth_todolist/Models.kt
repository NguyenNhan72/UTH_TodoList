package com.example.uth_todolist

import androidx.annotation.DrawableRes

//oop: lop dinh nghia 1 trang
data class OnboardingPage(
    @DrawableRes val imageRes: Int,
    val title: String,
    val description: String
)

//cac tuyen dieu huong
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
}