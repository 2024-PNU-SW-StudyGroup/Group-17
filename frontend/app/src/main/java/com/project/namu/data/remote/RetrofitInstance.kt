package com.project.namu.data.remote

import com.project.namu.model.AuthInterceptor
import com.project.namu.model.AuthRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://apptive-namu.ap-northeast-2.elasticbeanstalk.com/"

    @Volatile
    private var apiService: ApiService? = null

    fun getInstance(authRepository: AuthRepository, authInterceptor: AuthInterceptor): ApiService {
        return apiService ?: synchronized(this) {
            apiService ?: createRetrofitInstance(authInterceptor).also { apiService = it }
        }
    }


    private fun createRetrofitInstance(authInterceptor: AuthInterceptor): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor) // ✅ 외부에서 주입받은 Interceptor 사용
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return retrofit.create(ApiService::class.java) // ✅ 순수한 ApiService 객체만 반환
    }
}