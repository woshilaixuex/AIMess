package com.test.ganggod.database.chatHistory

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat_history")
data class ChatHistory(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userId:String,
    val model:String,
    val content:List<ChatMessage>
)
//{
//    "id" : 0,
//    "message" : ""
//     "role" : 0,
//     "model" : ""
//}
