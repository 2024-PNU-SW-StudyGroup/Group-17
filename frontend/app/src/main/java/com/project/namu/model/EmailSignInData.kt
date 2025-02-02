package com.project.namu.model

data class EmailSignInRequest(
    val user_name : String,
    val password : String,
    val email : String
)

data class EmailSignInResponse(
    val success : Boolean
)

