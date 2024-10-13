package com.test.ganggod.network.lottery

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LotteryService {
    @POST("/api/raffle/history")
    fun getLotteryHistory(@Body lotteryHistoryRequest: LotteryHistoryRequest):Flow<LotteryHistoryResponse>

    @POST("/api/raffle/award")
    suspend fun lottery(@Body lotteryRequest: LotteryRequest):LotteryResponse
}