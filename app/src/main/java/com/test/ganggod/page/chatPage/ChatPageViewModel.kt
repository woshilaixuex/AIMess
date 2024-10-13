package com.test.ganggod.page.chatPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.chat.ChatRequest
import com.test.ganggod.constData.UserData
import com.test.ganggod.constData.WebSocketUrl
import com.test.ganggod.database.chatHistory.ChatHistory
import com.test.ganggod.database.chatHistory.ChatHistoryDao
import com.test.ganggod.database.chatHistory.ChatMessage
import com.test.ganggod.network.chat.ChatResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

@HiltViewModel
class ChatPageViewModel @Inject constructor(
    private val chatHistoryDao: ChatHistoryDao
) : ViewModel() {
    private val _chatList = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatList = _chatList.asStateFlow()

    private val webSocket = MutableStateFlow<WebSocket?>(null)

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _dialogContent = MutableStateFlow("")
    val dialogContent = _dialogContent.asStateFlow()

    private val _currentModel = MutableStateFlow("gpt-4")
    val currentModel = _currentModel.asStateFlow()

    //当前聊天记录id
    private val _chatId = MutableStateFlow(0)
    val chatId = _chatId.asStateFlow()

    var newChat = false

    fun showDialog(content: String) {
        _showDialog.value = true
        _dialogContent.value = content
    }

    fun dismissDialog() {
        _showDialog.value = false
    }

    fun changeModel(model: String) {
        _currentModel.value = model
    }
    // 初始化执行
    init {
        initialWebSocket()
    }

    fun getChatId(id:Int)
    {
        _chatId.value = id
    }


    fun initChatRecord(chatId:Int) {
        Log.d("TAG", "initChatRecord: ${_chatId.value}")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val chatRecord = chatHistoryDao.getChatHistoryById(chatId,UserData.userId)
                    if (chatRecord == null){
                        newChat = true
                    }
                    chatRecord?.content?.forEach {
                        _chatList.value += it
                        Log.d("TAG", "updateChatHistory: ${_chatList.value}")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun reconnect() {
        initialWebSocket()
    }
    // 初始化websocket链接
    private fun initialWebSocket() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val request: Request = Request.Builder()
                    .url(WebSocketUrl.URL)
                    .build()

                webSocket.value = client.newWebSocket(request, object : WebSocketListener() {
                    override fun onOpen(webSocket: WebSocket, response: Response) {
                        super.onOpen(webSocket, response)
                        Log.d("TAG", "onOpen: open")
                    }

                    override fun onMessage(webSocket: WebSocket, text: String) {
                        super.onMessage(webSocket, text)
                        val response = Json.decodeFromString(ChatResponse.serializer(), text)
                        _chatList.value += ChatMessage(response.message,"assistant")
                        updateChatHistory()
                    }

                    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                        super.onMessage(webSocket, bytes)
                    }

                    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                        super.onClosing(webSocket, code, reason)
                        webSocket.close(1000, null)
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        super.onClosed(webSocket, code, reason)
                        Log.d("WebSocket已关闭:", "$code / $reason")
                        // 尝试重新连接
                        reconnect()
                    }

                    override fun onFailure(
                        webSocket: WebSocket,
                        t: Throwable,
                        response: Response?
                    ) {
                        super.onFailure(webSocket, t, response)
                        Log.d("tag", "WebSocket连接失败: ${t.message}")
                        // 尝试重新连接
                        reconnect()
                    }
                })
            }
        }
    }

    fun send(message: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val chat = Json.encodeToString(ChatRequest(UserData.userId,1,message))
                Log.d("TAG", "send: $chat")
                while (true) {
                    try {
                        val isSuccess = webSocket.value?.send(chat)
                        Log.d("TAG", "send: $isSuccess")
                        if (isSuccess == true)
                            break
                    } catch (e: Exception) {
                        e.printStackTrace()
                        reconnect()
                    }
                }
                _chatList.value += ChatMessage(message,"user")
                updateChatHistory()
            }
        }
    }

    private fun updateChatHistory() {
        val chatList = _chatList.value
        Log.d("TAG", "-------------chatlist: $chatList")
//        val chatMessage = chatList.map { message ->
//            Log.d("TAG", "updateChatHistory: $message,${_currentModel.value}")
//            ChatMessage(message.content, message.role)
//        }
//        Log.d("TAG", "-------------: $chatMessage")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    if (newChat) {
                        chatHistoryDao.insertChatHistory(
                            ChatHistory(
                                id = 0,
                                userId = UserData.userId,
                                model = _currentModel.value,
                                content = chatList
                            )
                        )
                        newChat = false
                        _chatId.value = chatHistoryDao.getChatHistorySize(userId = UserData.userId)
                    }
                    else
                        chatHistoryDao.updateChatHistory(_chatId.value, UserData.userId,chatList)
                } catch (e: Exception) {
                    Log.d("database", "updateChatHistory: ${e.message}")
                }
            }
        }
    }


}
