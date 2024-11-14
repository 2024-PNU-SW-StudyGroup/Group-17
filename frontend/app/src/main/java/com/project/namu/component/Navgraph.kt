package com.project.namu.component



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost



@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "") {

    }
}