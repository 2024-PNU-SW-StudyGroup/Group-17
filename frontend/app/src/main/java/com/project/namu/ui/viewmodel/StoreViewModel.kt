package com.project.namu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.data.model.StoreData
import com.project.namu.data.repository.StoreRepository
import com.project.namu.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

sealed class StoreUiState {
    object Loading : StoreUiState()
    data class Success(val data: List<StoreData>) : StoreUiState()
    data class Error(val message: String) : StoreUiState()
}

class StoreViewModel : ViewModel() {

    private val repository = StoreRepository(RetrofitInstance.apiService)

    private val _uiState = mutableStateOf<StoreUiState>(StoreUiState.Loading)
    val uiState: State<StoreUiState> get() = _uiState

    init {
        fetchStores()
    }

    private fun fetchStores() {
        viewModelScope.launch {
            try {
                val response = repository.getStoreList()
                if (response.isSuccessful) {
                    // response.body()가 곧 List<StoreData>이므로, null 처리
                    val storeList = response.body() ?: emptyList()
                    _uiState.value = StoreUiState.Success(storeList)
                } else {
                    _uiState.value = StoreUiState.Error("서버 오류: ${response.code()}")
                }
            } catch (e: Exception) {
                _uiState.value = StoreUiState.Error("네트워크 오류: ${e.localizedMessage}")
            }
        }
    }
}