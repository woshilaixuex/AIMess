package com.test.ganggod.network.register

import com.test.ganggod.network.login.UserResponseData
import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val code:Int,
    val message:String,
    val data: UserResponseData
)
