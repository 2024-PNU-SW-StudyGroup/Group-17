package com.project.namu.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.namu.login.PopUpViewModel
import com.project.namu.navigation.Screen
import com.project.namu.network.namuService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LogInViewModel : ViewModel(
){
    private val _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess : StateFlow<Boolean> = _isSuccess

    private val _userID = MutableStateFlow<Int>(0)
    val userID : StateFlow<Int> = _userID

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }


    fun postLoginData(request : EmailLogInRequest, navController: NavController, popUpViewModel: PopUpViewModel, authViewModel: AuthViewModel){
        Log.d("LogInViewModel", "서버 응답 코드: $request")
        viewModelScope.launch {
            try{
                val response = namuService.logInRequest(request)
                _isSuccess.value = true

                authViewModel.saveToken(response.data.accessToken, response.data.refreshToken)

                //_userID.value = response.userID
                // ✅ 응답 바디 출력
                Log.d("LogInViewModel", "서버 응답 성공: $response")
                navController.navigate(Screen.Signin.route) // 성공하면 바로 이동

            } catch(e:HttpException){ //4xx 5xx 오류 처리
                if (e.code() == 401 ){
                    Log.e("LogInViewModel", "HT 오류 발생: ${e.code()} - ${e.message()}", e)
                    _isSuccess.value = false
                    popUpViewModel.showDialog()

                }
                _isSuccess.value = false
                popUpViewModel.showDialog()

            }
            catch (e:Exception){
                Log.e("LogInViewModel", "네트워크 오류 또는 예외 발생: ${e.message}", e)
                _isSuccess.value = false


            }
        }
    }
}