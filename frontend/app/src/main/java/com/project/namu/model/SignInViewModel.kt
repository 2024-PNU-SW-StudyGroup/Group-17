package com.project.namu.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.network.namuService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SignInViewModel :ViewModel() {
    private val _signInMessage = MutableStateFlow<String> ("")
    val signInMessage : StateFlow<String> = _signInMessage

    fun fetchSignInSuccess(request: EmailSignInRequest){
        viewModelScope.launch {
            try{
                 namuService.signUpRequest(request)
                _signInMessage.value = "success"

            } catch (e : Exception){

                _signInMessage.value = "fail"

            }

        }
    }


}