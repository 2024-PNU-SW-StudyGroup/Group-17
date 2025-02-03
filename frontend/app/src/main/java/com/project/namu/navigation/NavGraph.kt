package com.project.namu.navigation


import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.namu.login.Login
import com.project.namu.login.PopUpViewModel
import com.project.namu.login.SignIn
import com.project.namu.model.LogInViewModel
import com.project.namu.model.MyPageViewModel
import com.project.namu.model.SignInViewModel
import com.project.namu.mypage.MyPage
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
        composable(Screen.Login.route) { Login(PopUpViewModel = popUpViewModel, LogInViewModel = logInViewModel, navController)}
        composable(Screen.Signin.route) { SignIn(popUpViewModel = popUpViewModel, signInViewModel = signInViewModel, navController) }
        composable(Screen.MyPage.route) { MyPage(MyPageViewModel = myPageViewModel ) }
    }
}