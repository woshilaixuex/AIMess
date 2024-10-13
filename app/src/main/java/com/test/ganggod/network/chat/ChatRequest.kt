package com.example.myapplication.network.chat

import kotlinx.serialization.*

@Serializable
data class ChatRequest(
    val user_id:String,
    val model_id:Int,
    val message:String
)
