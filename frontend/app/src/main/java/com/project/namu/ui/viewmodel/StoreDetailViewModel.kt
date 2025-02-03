package com.project.namu.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.data.model.StoreDetailData
import com.project.namu.data.remote.RetrofitInstance
import com.project.namu.data.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StoreDetailViewModel : ViewModel() {

    private val repository = StoreRepository(RetrofitInstance.apiService)

    private val _uiState = MutableStateFlow<StoreDetailUiState>(StoreDetailUiState.Loading)
    val uiState: StateFlow<StoreDetailUiState> = _uiState

    fun getStoreDetail(storeId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.getStoreDetail(storeId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _uiState.value = StoreDetailUiState.Success(body)
                    } else {
                        _uiState.value = StoreDetailUiState.Error("서버 응답이 비어 있습니다.")
                    }
                } else {
                    _uiState.value = StoreDetailUiState.Error("에러 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = StoreDetailUiState.Error(e.message ?: "알 수 없는 에러")
            }
        }
    }
}


sealed class StoreDetailUiState {
    object Loading : StoreDetailUiState()
    data class Success(val data: StoreDetailData) : StoreDetailUiState()
    data class Error(val message: String) : StoreDetailUiState()
}