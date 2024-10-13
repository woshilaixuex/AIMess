package com.test.ganggod.network.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val code:Int,
    val msg:String,
    val data:UserResponseData
)
@Serializable
data class UserResponseData(
    val user_id:String,
    val username:String
)