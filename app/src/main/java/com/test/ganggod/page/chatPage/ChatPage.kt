package com.test.ganggod.page.chatPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.test.ganggod.R
import kotlinx.coroutines.launch

@Composable
fun ChatPage(
    navHostController: NavHostController,
    chatId: Int,
    chatPageViewModel: ChatPageViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        chatPageViewModel.initChatRecord(chatId)
    }
    chatPageViewModel.getChatId(chatId)
    var currentMessage by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current //键盘
    val chatList by chatPageViewModel.chatList.collectAsState()
    val showDialog by chatPageViewModel.showDialog.collectAsState()
    val dialogContent by chatPageViewModel.dialogContent.collectAsState() //弹窗消息
    val currentModel by chatPageViewModel.currentModel.collectAsState()

    fun sendMessage() {
        if (currentMessage.text.isNotEmpty()) {
            chatPageViewModel.send(currentMessage.text)
            scope.launch {
                currentMessage = TextFieldValue("")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { chatPageViewModel.dismissDialog() },
            title = { Text("错误") },
            text = { Text(dialogContent) },
            confirmButton = {
                Button(onClick = { chatPageViewModel.dismissDialog() }) {
                    Text("确定")
                }
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
    ) {
        TopBar(currentModel = currentModel, onModelChange = {}){
            navHostController.popBackStack()
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            state = listState
        ) {
            items(chatList.size) { index ->
                val message = chatList[index]
                if (message.role == "assistant")
                    AiMessage(message.content)
                else {
                    UserMessage(message = message.content)
                }
            }
        }
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = currentMessage,
                onValueChange = { currentMessage = it },

                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface, CircleShape)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(1f),
                maxLines = 4,
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .wrapContentHeight()
                    ) {
                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (currentMessage.text.isEmpty()) {
                                Text("Enter your message", color = Color.Gray)
                            }
                            innerTextField()
                        }
                        IconButton(
                            onClick = {
                                sendMessage()
                                keyboardController?.hide()
                            },
                        ) {
                            Icon(Icons.Filled.Send, null)
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun UserMessage(message: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
        IconWithBackground(true)
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            MessageText(message)
        }
    }
}


@Composable
fun AiMessage(message: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .wrapContentSize(align = Alignment.TopCenter)
    ) {
        IconWithBackground(false)
        Column(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            MessageText(message)
        }
    }
}

@Composable
fun IconWithBackground(isUser: Boolean, modifier: Modifier = Modifier) {
    Icon(
        painterResource(id = if (isUser) R.drawable.user else R.drawable.openai),
        tint = Color.Unspecified,
        contentDescription = "Icon",
        modifier = modifier
            .size(48.dp)
            .padding(4.dp)
            .background(
                color = Color("#10A37F".toColorInt()),
                shape = CircleShape
            )
            .padding(4.dp)
    )
}

@Composable
fun MessageText(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(12.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}








