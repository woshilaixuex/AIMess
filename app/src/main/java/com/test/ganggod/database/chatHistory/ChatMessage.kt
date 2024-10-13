package com.test.ganggod.database.chatHistory

import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val content: String,
    val role:String, // assistant,user
)

