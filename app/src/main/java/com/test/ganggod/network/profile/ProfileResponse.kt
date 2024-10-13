package com.test.ganggod.network.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val code: Int,
    val message: String,
    val data: ProfileResponseData
){
    @Serializable
    data class ProfileResponseData(
        val user_id: String,
        val username: String,
        val email: String,
        val credit: Int
    )
}

