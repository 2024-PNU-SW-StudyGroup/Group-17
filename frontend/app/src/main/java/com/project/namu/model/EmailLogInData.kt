package com.project.namu.model

import com.google.gson.annotations.SerializedName

data class EmailLogInRequest(
    val email : String,
    val password : String

)

data class EmailLogInResponse(
    val data : Data
)

data class Data (
    val userId : Int,
    val accessToken : String,
    val refreshToken : String
)



