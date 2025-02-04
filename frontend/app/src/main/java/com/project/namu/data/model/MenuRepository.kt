package com.project.namu.data.repository

import com.project.namu.data.remote.ApiService
import javax.inject.Inject


class MenuRepository @Inject constructor(  // ✅ Hilt가 주입할 수 있도록 @Inject 추가
    private val apiService: ApiService
) {
    suspend fun fetchMenuDetail(menuId: Int) = apiService.getMenuDetail(menuId)
}