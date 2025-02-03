package com.project.namu.ui.component

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.namu.ui.page.HomeScreen
import com.project.namu.ui.page.LoginScreen
import com.project.namu.ui.page.MypageScreen
import com.project.namu.ui.page.Search_listScreen
import com.project.namu.ui.page.StoreScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "검색") {

        composable("로그인") { LoginScreen(navController) }
        composable("회원가입") { MypageScreen(navController) }
        composable("홈") { HomeScreen(navController) }

        // 검색 화면
        composable("검색") {
            Search_listScreen(navController)
        }

        // 가게 상세 화면 (storeId를 Path 파라미터로 받아옴)
        composable(
            route = "가게상세/{storeId}",
            arguments = listOf(navArgument("storeId") { type = NavType.IntType })
        ) { backStackEntry ->
            // NavBackStackEntry로부터 storeId를 꺼냄
            val storeId = backStackEntry.arguments?.getInt("storeId") ?: 0

            // StoreScreen에 넘겨주어 상세 데이터를 불러오고 화면을 구성
            StoreScreen(navController = navController, storeId = storeId)
        }
    }
}