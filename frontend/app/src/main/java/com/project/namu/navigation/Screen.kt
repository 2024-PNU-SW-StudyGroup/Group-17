package com.project.namu.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signin : Screen("signin")

    object MyPage : Screen("mypage")
}