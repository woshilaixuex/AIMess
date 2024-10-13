package com.test.ganggod.network.login

import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("/api/user/login")
    suspend fun login(@Body loginRequest: LoginRequest):LoginResponse
}