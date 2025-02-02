package com.project.namu.model

data class MyPageResponse(
    val isSuccess : Boolean,
    val message : String,
    val data : UserResponse
)



   data class UserResponse(
      val profile_url : String,
       val user_name : String,
       val total_discount : Int,
       val isOrder : Boolean,
       val store_picture_url : String,
       val pickup_time : String
   )