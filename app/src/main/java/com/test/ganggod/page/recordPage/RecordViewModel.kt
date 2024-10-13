package com.test.ganggod.page.recordPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.ganggod.constData.UserData
import com.test.ganggod.database.chatHistory.ChatHistory
import com.test.ganggod.database.chatHistory.ChatHistoryDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val chatHistoryDao: ChatHistoryDao,
) : ViewModel() {

    private val _conversationHistory = MutableStateFlow<List<ChatHistory>>(emptyList())
    val conversationHistory = _conversationHistory.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                chatHistoryDao.getAllChatHistory(UserData.userId)
                    .flowOn(Dispatchers.IO)
                    .collect { chatHistories ->
                        _conversationHistory.value = chatHistories
                        Log.d("RecordViewModel", "Collected chat histories: $chatHistories")
                    }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }
}