package com.test.ganggod.network.lottery

import kotlinx.serialization.Serializable

@Serializable
data class LotteryRequest(
    val user_id:String,
    val strategy_id:Int
)
