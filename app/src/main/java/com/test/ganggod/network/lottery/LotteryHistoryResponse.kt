package com.test.ganggod.network.lottery

import kotlinx.serialization.Serializable

@Serializable
data class LotteryHistoryResponse(
    val code:Int,
    val msg:String,
    val data:LotteryHistoryData
) {
    @Serializable
    data class LotteryHistoryData(
        val page:Int,
        val total_pages:Int,
        val historys:List<String>
    )
}