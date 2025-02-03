package com.project.namu.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,  // ✅ 토큰 관리
    private val authInterceptor: AuthInterceptor
):ViewModel() {
    private val apiService = ApiClient.getInstance(authRepository, authInterceptor)

    private val _signInMessage = MutableStateFlow<String> ("")
    val signInMessage : StateFlow<String> = _signInMessage

    fun fetchSignInSuccess(request: EmailSignInRequest){
        viewModelScope.launch {
            try{
                 apiService.signUpRequest(request)
                _signInMessage.value = "success"

            } catch (e : Exception){

                _signInMessage.value = "fail"

            }

        }
    }


}