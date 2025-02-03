package com.project.namu.login

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.project.namu.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PopUpViewModel : ViewModel(){

    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible = _isDialogVisible.asStateFlow()

    private val _toLogin = MutableStateFlow("unavailable")
    val toLogin = _toLogin.asStateFlow()


    fun showDialog(){
        _isDialogVisible.value = true
    }

    fun hideDialog(navController: NavController){
        _isDialogVisible.value = false
        if(_toLogin.value == "available"){
            navController.navigate(Screen.Login.route)
            _toLogin.value = "unavailable"
        }
    }

    fun accessToLogin(){
        _toLogin.value = "available"
    }
}