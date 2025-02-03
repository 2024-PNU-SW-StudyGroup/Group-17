package com.project.namu.model

import com.project.namu.data.remote.ApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authRepository: AuthRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // ✅ DataStore에서 Access Token 가져오기
        val accessToken = runBlocking { authRepository.accessToken.first() }

        // ✅ 특정 API 요청에만 토큰을 추가하도록 설정
        val shouldAttachToken = originalRequest.url.encodedPath in listOf(
            "login", "mypage"
        )

        val requestBuilder: Request.Builder = originalRequest.newBuilder()
        if (shouldAttachToken && !accessToken.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
        }

        val response = chain.proceed(requestBuilder.build())

        // ✅ AT가 만료된 경우 (401 Unauthorized)
        if (response.code == 401 && shouldAttachToken) {
            synchronized(this) {
                val refreshToken = runBlocking { authRepository.refreshToken.first() }

                val newAccessToken = refreshToken?.let { requestNewAccessToken(it) }

                return if (newAccessToken != null) {
                    runBlocking { authRepository.saveToken(newAccessToken, refreshToken) }

                    val newRequest = originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer $newAccessToken")
                        .build()

                    chain.proceed(newRequest)
                } else {
                    response
                }
            }
        }

        return response
    }

    // ✅ 해결 방법: Retrofit을 동적으로 생성해서 호출
    private fun requestNewAccessToken(refreshToken: String): String? {
        return try {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://apptive-namu.ap-northeast-2.elasticbeanstalk.com/") // ✅ 서버 주소
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java) // ✅ 여기서만 동적으로 `ApiService` 생성
            val response = runBlocking {
                apiService.refreshAccessToken("Bearer $refreshToken")
            }

            if (response.isSuccess) {
                response.data // ✅ 새로운 AT 반환
            } else {
                null
            }
        } catch (e: IOException) {
            null
        }
    }
}
