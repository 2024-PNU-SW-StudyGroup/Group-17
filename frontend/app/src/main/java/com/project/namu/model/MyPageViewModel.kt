package com.project.namu.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.network.namuService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
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

                val response = namuService.getMyPage(userID)
                _user_name.value = response.data.user_name
                _profile_url.value = response.data.profile_url
                _total_discount.value = response.data.total_discount

                if(response.data.isOrder){
                    _isOrderMessage.value = true
                }

            } catch (e : Exception){

            }
        }
    }

}

