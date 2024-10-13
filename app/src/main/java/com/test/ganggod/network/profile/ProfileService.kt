package com.test.ganggod.network.profile

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileService {
    @GET("/api/user/info")
    suspend fun getProfile(@Query("user_id")userId:String):ProfileResponse

    @POST("/api/message/ai_models")
    suspend fun getAiModels(@Body aiModelsRequest:AiModelsRequest):AiModelsResponse
}