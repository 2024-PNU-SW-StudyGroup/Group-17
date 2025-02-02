package com.project.namu.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.namu.login.Login
import com.project.namu.login.PopUpViewModel
import com.project.namu.login.SignIn
import com.project.namu.model.AuthViewModel
import com.project.namu.model.LogInViewModel
import com.project.namu.model.SignInViewModel

@Composable
fun NavGraph(navController: NavHostController, startDestination: String = Screen.Login.route){

    val PopUpViewModel : PopUpViewModel = viewModel()
    val LogInViewModel : LogInViewModel = viewModel ()
    val SignInViewModel : SignInViewModel = viewModel()
    val AuthViewModel : AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        composable(Screen.Login.route) { Login(PopUpViewModel = PopUpViewModel, LogInViewModel = LogInViewModel, navController, AuthViewModel= AuthViewModel)}
        composable(Screen.Signin.route) { SignIn(popUpViewModel = PopUpViewModel, signInViewModel = SignInViewModel, navController) }
    }
}