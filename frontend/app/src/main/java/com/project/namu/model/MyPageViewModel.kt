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
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthRepository,  // ✅ AT & RT 관리용 Repository 추가
    private val authInterceptor: AuthInterceptor
) : ViewModel() {
    // ✅ API 요청을 `ApiClient`를 통해 보냄 (AT 자동 추가 & 만료 시 RT 갱신)
    private val apiService = ApiClient.getInstance(authRepository, authInterceptor)

    private val _isOrderMessage = MutableStateFlow<Boolean> (false)
    val isOrderMessage : StateFlow<Boolean> = _isOrderMessage

    private val _user_name = MutableStateFlow<String>("")
    val user_name : StateFlow<String> = _user_name

    private val _profile_url = MutableStateFlow<String>("")
    val profile_url : StateFlow<String> = _profile_url

    private val _total_discount = MutableStateFlow<Int>(0)
    val total_discount : StateFlow<Int> = _total_discount

    fun fetchMyPageData(userID : Int){
        viewModelScope.launch {
            try{

                Log.d("MyPageViewModel", "서버에 MyPage 데이터 요청 중... (userID: $userID)")

                val response = apiService.getMyPage(userID)

                Log.d("MyPageViewModel", "서버 응답 성공: ${response}")
                _user_name.value = response.data.user_name
                _profile_url.value = response.data.profile_url
                _total_discount.value = response.data.total_discount

                if(response.data.isOrder){
                    _isOrderMessage.value = true
                }

            } catch (e : Exception){

                Log.e("MyPageViewModel", "서버 요청 실패: ${e.message}", e)

            }
        }
    }

}

