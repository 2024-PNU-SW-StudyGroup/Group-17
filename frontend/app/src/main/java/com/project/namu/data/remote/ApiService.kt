package com.project.namu.data.remote

import com.project.namu.data.model.StoreData
import com.project.namu.data.model.StoreDetailData
import com.project.namu.model.AuthInterceptor
import com.project.namu.model.AuthRepository
import com.project.namu.model.EmailLogInRequest
import com.project.namu.model.EmailLogInResponse
import com.project.namu.model.EmailSignInRequest
import com.project.namu.model.EmailSignInResponse
import com.project.namu.model.MyPageResponse
import com.project.namu.model.RefreshResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


object ApiClient {
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

interface ApiService{
    @GET("mypage/{userId}")
    suspend fun getMyPage(@Path("userId") userId : Int) : MyPageResponse

    @POST("signup")
    suspend fun signUpRequest(
        @Body request : EmailSignInRequest
    ) : EmailSignInResponse

    @POST("login")
    suspend fun logInRequest(
        @Body request: EmailLogInRequest
    ): EmailLogInResponse // 로그 찍을땐 Response<T>로 변경

    @POST("auth/refresh") // 기존 AT 만료시 서버에서 RT로 새로운 AT 발급하는 API
    suspend fun refreshAccessToken(
        @Header("Authorization") refreshToken: String
    ): RefreshResponse

    @GET("store/list")
    // 최상위가 배열이므로, Response<List<StoreData>>로 선언
    suspend fun getStoreList(): Response<List<StoreData>>

    @GET("store/{storeId}")
    suspend fun getStoreDetail(@Path("storeId") storeId: Int): Response<StoreDetailData>

}