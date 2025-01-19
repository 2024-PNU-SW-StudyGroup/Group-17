package com.project.namu.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PopUpViewModel : ViewModel(){

    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible = _isDialogVisible.asStateFlow()

    fun showDialog(){
        _isDialogVisible.value = true
    }

    fun hideDialog(){
        _isDialogVisible.value = false
    }
}