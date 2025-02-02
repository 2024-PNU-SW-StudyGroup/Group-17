package com.project.namu.model

data class EmailLogInRequest(
    val email : String,
    val password : String

)

data class EmailLogInResponse(
    val userID :Int,
    val username : String,
    val password: String
)

