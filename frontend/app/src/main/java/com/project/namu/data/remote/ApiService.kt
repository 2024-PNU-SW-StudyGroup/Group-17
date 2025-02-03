package com.project.namu.data.remote
import com.project.namu.data.model.StoreData
import com.project.namu.data.model.StoreDetailData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("store/list")
    // 최상위가 배열이므로, Response<List<StoreData>>로 선언
    suspend fun getStoreList(): Response<List<StoreData>>

    @GET("store/{storeId}")
    suspend fun getStoreDetail(@Path("storeId") storeId: Int): Response<StoreDetailData>
}