package com.test.ganggod.network.lottery

import kotlinx.serialization.Serializable

@Serializable
data class LotteryResponse(
    val code:Int,
    val message:String,
    val data:LotteryData
) {
    @Serializable
    data class LotteryData(
        val award_id:Int,
        val award_title:String//获奖详情
    )
}
