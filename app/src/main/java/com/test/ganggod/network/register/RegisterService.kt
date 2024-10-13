package com.test.ganggod.network.register

import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/api/user/register")
    suspend fun register(@Body registerRequest: RegisterRequest):RegisterResponse
}