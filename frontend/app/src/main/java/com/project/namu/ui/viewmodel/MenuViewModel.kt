package com.project.namu.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.namu.data.model.MenuDetailData
import com.project.namu.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MenuDetailUiState {
    object Loading : MenuDetailUiState()
    data class Success(val data: MenuDetailData) : MenuDetailUiState()
    data class Error(val message: String) : MenuDetailUiState()
}

// ✅ HiltViewModel 추가
@HiltViewModel  // ✅ Hilt ViewModel 어노테이션 추가
class MenuViewModel @Inject constructor(  // ✅ Hilt가 MenuRepository를 주입할 수 있도록 @Inject 추가
    private val repository: MenuRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MenuDetailUiState>(MenuDetailUiState.Loading)
    val uiState: StateFlow<MenuDetailUiState> = _uiState

    fun getMenuDetail(menuId: Int) {
        viewModelScope.launch {
            try {
                val response = repository.fetchMenuDetail(menuId)
                if (response.isSuccess) {
                    _uiState.value = MenuDetailUiState.Success(response.data)
                } else {
                    _uiState.value = MenuDetailUiState.Error(response.message)
                }
            } catch (e: Exception) {
                _uiState.value = MenuDetailUiState.Error("데이터를 불러오는 중 오류가 발생했습니다.")
            }
        }
    }
}