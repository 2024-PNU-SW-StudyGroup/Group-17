package com.project.namu.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.project.namu.data.remote.ApiClient
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

    private val _signInMessage = MutableStateFlow<String> ("fail")
    val signInMessage : StateFlow<String> = _signInMessage

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _phoneNumber = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordDone = MutableStateFlow(false)
    val passwordDone: StateFlow<Boolean> = _passwordDone

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePhoneNumber(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun setPasswordDone(isDone: Boolean) {
        _passwordDone.value = isDone
    }

    fun successSignInMessage(alertMessage : String){
        if (alertMessage == "success"){
            _signInMessage.value = "회원가입이 완료되었어요."
        }
        else{
            _signInMessage.value =  "아이디 또는 비밀번호를\n 확인해 주세요."
        }
    }

    fun fetchSignInSuccess(){
        val request = EmailSignInRequest(userName = _name.value, password = _password.value, email = _email.value )
        viewModelScope.launch {
            try{
                 apiService.signUpRequest(request)
                Log.d("sign","$request")



            } catch (e : Exception){



            }

        }
    }


}