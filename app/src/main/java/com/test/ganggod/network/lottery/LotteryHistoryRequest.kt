package com.test.ganggod.network.lottery

import kotlinx.serialization.Serializable

@Serializable
data class LotteryHistoryRequest(
    val user_id:String,
    val page:Int
)
