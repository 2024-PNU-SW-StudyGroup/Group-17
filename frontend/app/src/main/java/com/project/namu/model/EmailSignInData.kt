package com.project.namu.model

data class EmailSignInRequest(
    val userName : String,
    val password : String,
    val email : String
)

data class EmailSignInResponse(
    val success : Boolean
)

