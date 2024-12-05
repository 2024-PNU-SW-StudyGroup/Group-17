package com.project.namu.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.namu.page.HomeScreen
import com.project.namu.page.LoginScreen
import com.project.namu.page.MypageScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "survey") {
        composable("로그인") { LoginScreen(navController) }
        composable("회원가입") { MypageScreen(navController) }
        composable("홈") { HomeScreen(navController) }
    }
}