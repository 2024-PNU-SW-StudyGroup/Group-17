package com.project.namu.network

import com.project.namu.model.EmailLogInRequest
import com.project.namu.model.EmailLogInResponse
import com.project.namu.model.EmailSignInRequest
import com.project.namu.model.EmailSignInResponse
import com.project.namu.model.MyPageResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private val retrofit = Retrofit.Builder()
    .baseUrl("http://apptive-namu.ap-northeast-2.elasticbeanstalk.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val namuService = retrofit.create(ApiService::class.java)

interface ApiService{
    @GET("mypage/{userId}")
    suspend fun getMyPage(@Path("userID") userID : Int) : MyPageResponse

    @POST("signup")
    suspend fun signUpRequest(
        @Body request : EmailSignInRequest
    ) : EmailSignInResponse

    @POST("/login")
    suspend fun logInRequest(@Body request: EmailLogInRequest): Response<EmailLogInResponse> // ✅ Response<T>로 변경
}