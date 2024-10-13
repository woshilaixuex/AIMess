package com.test.ganggod.database.chatHistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatHistoryDao {
    @Insert
    suspend fun insertChatHistory(chatHistory: ChatHistory)

    @Query("UPDATE CHAT_HISTORY SET content = :historyList WHERE id = :id AND userId = :userId")
    suspend fun updateChatHistory(id:Int,userId: String,historyList:List<ChatMessage>)

    @Query("SELECT * FROM CHAT_HISTORY WHERE id = :id AND userId = :userId")
    suspend fun getChatHistoryById(id:Int,userId: String): ChatHistory?

    @Query("SELECT * FROM CHAT_HISTORY WHERE userId = :userId")
    fun getAllChatHistory(userId:String):Flow<List<ChatHistory>>

    @Query("DELETE FROM CHAT_HISTORY WHERE id = :id AND userId = :userId")
    suspend fun deleteChatHistoryById(id:Int,userId: String):Int

    @Query("SELECT COUNT(*) FROM CHAT_HISTORY WHERE userId = :userId")
    suspend fun getChatHistorySize(userId: String):Int

}