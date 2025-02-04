package com.project.namu.navigation


import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.namu.login.Login
import com.project.namu.login.PopUpViewModel
import com.project.namu.login.SignIn
import com.project.namu.model.LogInViewModel
import com.project.namu.model.MyPageViewModel
import com.project.namu.model.SignInViewModel
import com.project.namu.mypage.MyPage
import com.project.namu.ui.page.HomeScreen
import com.project.namu.ui.page.MenuScreen
import com.project.namu.ui.page.Search_listScreen
import com.project.namu.ui.page.StoreScreen
import com.project.namu.ui.page.WishListScreen
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun NavGraph(navController: NavHostController, startDestination: String = Screen.Login.route){

    val popUpViewModel : PopUpViewModel = hiltViewModel()
    val logInViewModel : LogInViewModel = hiltViewModel()
    val signInViewModel : SignInViewModel = hiltViewModel()
    val myPageViewModel :MyPageViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screen.Login.route) { Login(PopUpViewModel = popUpViewModel, LogInViewModel = logInViewModel, navController, signInViewModel = signInViewModel)}
        composable(Screen.Signin.route) { SignIn(popUpViewModel = popUpViewModel, signInViewModel = signInViewModel, navController = navController, logInViewModel = logInViewModel) }
        composable(Screen.MyPage.route) { MyPage(MyPageViewModel = myPageViewModel, logInViewModel= logInViewModel ) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Search.route) { Search_listScreen(navController) }
        composable(Screen.WishList.route) { WishListScreen(navController) }


        // 가게 상세 화면 (storeId를 Path 파라미터로 받아옴)
        composable(
            route = "가게상세/{storeId}",
            arguments = listOf(navArgument("storeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val storeId = backStackEntry.arguments?.getInt("storeId") ?: 0
            StoreScreen(navController = navController, storeId = storeId)
        }

        composable(
            route = "menu_detail/{menuId}",
            arguments = listOf(navArgument("menuId") { type = NavType.IntType })
        ) { backStackEntry ->
            val menuId = backStackEntry.arguments?.getInt("menuId") ?: 0
            MenuScreen(navController = navController, menuId = menuId)
        }

    }
}