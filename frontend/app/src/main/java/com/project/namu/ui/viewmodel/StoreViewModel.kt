package com.project.namu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.data.model.StoreData
import com.project.namu.data.repository.StoreRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.project.namu.model.AuthRepository
import com.project.namu.model.AuthInterceptor
import com.project.namu.data.remote.RetrofitInstance
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class StoreUiState {
    object Loading : StoreUiState()
    data class Success(val data: List<StoreData>) : StoreUiState()
    data class Error(val message: String) : StoreUiState()
}

@HiltViewModel
class StoreViewModel @Inject constructor(
    authRepository: AuthRepository,
    authInterceptor: AuthInterceptor
) : ViewModel() {

    private val repository = StoreRepository(RetrofitInstance.getInstance(authRepository, authInterceptor))

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
                    val storeList = response.body() ?: emptyList() // ✅ null 안전 처리
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
