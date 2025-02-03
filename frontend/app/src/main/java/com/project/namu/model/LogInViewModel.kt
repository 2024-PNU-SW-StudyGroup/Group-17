package com.project.namu.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.namu.login.PopUpViewModel
import com.project.namu.navigation.Screen
import com.project.namu.network.ApiClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authInterceptor: AuthInterceptor // ✅ Interceptor 주입 추가// ✅ 토큰 관리
) : ViewModel(){
    private val _isSuccess = MutableStateFlow<Boolean>(false)
    val isSuccess : StateFlow<Boolean> = _isSuccess

    private val _userId = MutableStateFlow<Int>(0)
    val userId : StateFlow<Int> = _userId

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

    fun postLoginData(
        request: EmailLogInRequest,
        navController: NavController,
        popUpViewModel: PopUpViewModel
    ) {
        Log.d("LogInViewModel", "로그인 시도 요청: $request")

        viewModelScope.launch {
            try {

                    val apiService = ApiClient.getInstance(authRepository, authInterceptor)
                    val response = apiService.logInRequest(request)

                    // ✅ 새로운 AT & RT 저장
                    authRepository.saveToken(response.data.accessToken, response.data.refreshToken)

                    _isSuccess.value = true

                    _userId.value = response.data.userId

                    // ✅ 성공 로그 및 이동
                    Log.d("LogInViewModel", "서버 응답 성공: $response")
                    navController.navigate(Screen.MyPage.route)


            } catch (e: HttpException) { // 4xx, 5xx 오류 처리
                Log.e("LogInViewModel", "HTTP 오류 발생: ${e.code()} - ${e.message()}", e)
                _isSuccess.value = false
                popUpViewModel.showDialog()
            } catch (e: Exception) {
                Log.e("LogInViewModel", "네트워크 오류 또는 예외 발생: ${e.message}", e)
                _isSuccess.value = false
                popUpViewModel.showDialog()
            }
        }
    }


}