package com.project.namu.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel (private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken

    fun loadToken(){
        viewModelScope.launch {
            authRepository.accessToken.collect{
                token -> _accessToken.value = token
            }
        }
    }


    fun saveToken (
        accessToken: String,
        refreshToken : String){
        viewModelScope.launch {
            try{
                authRepository.saveToken(accessToken,refreshToken)}
            catch(e: Exception){

            }
        }
    }

}