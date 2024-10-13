package com.test.ganggod.network.chat

import kotlinx.serialization.Serializable

@Serializable
data class ChatResponse(
    val message:String,
    val timestamp:String,
    val end:Boolean
)
