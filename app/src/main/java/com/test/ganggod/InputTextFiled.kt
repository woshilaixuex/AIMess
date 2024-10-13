package com.test.ganggod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.ganggod.constData.ScreenParam
import com.test.ganggod.ui.theme.LightModeColor
import com.test.ganggod.utils.ScreenConstrainUtils

@Composable
@Preview
fun InputTextField() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(LightModeColor.BackGroundColor)
    ) {
        var currentMessage by remember { mutableStateOf(TextFieldValue("")) }

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
                        modifier = Modifier.padding(horizontal = 10.dp)
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
                            },
                        ) {
                            Icon(Icons.Filled.Send, null)
                        }
                    }
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(36.dp)
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.camera),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(36.dp)

                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.picture),
                    contentDescription = null
                )
            }
            IconButton(
                onClick = {
                },
                modifier = Modifier
                    .padding(8.dp)
                    .height(36.dp)
                    .weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.document),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun TopBarTest() {
    ScreenConstrainUtils.GetScreenWidthHeight()
    ScreenConstrainUtils.GetScreenMetrics()
    ScreenParam.logAll()
    var showBar by remember {
        mutableStateOf(false)
    }
    var model by remember {
        mutableStateOf("gpt-4o")
    }
    val items = listOf(
        "gpt-4o",
        "kimi",
        "gpt-4o-mini",
        "claude-3.5-sonnet",
        "claude-3-haiku",
        "gpt-4o-all",
        "gpt-4-turbo",
        "more model"
    )
    val paddings = 8f
    val strokeWidth = 4f
    Column(
        modifier = Modifier
            .background(LightModeColor.BackGroundColor)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightModeColor.TopBarColor)
        ) {
            Icon(
                painterResource(id = R.drawable.record),
                contentDescription = "model",
                modifier = Modifier
                    .padding(12.dp)
                    .size(48.dp)
                    .clickable {
                        showBar = !showBar
                    }
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = model,
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(
                        PaddingValues(8.dp)
                    )
            )
            Icon(
                painterResource(id = R.drawable.model),
                contentDescription = "model",
                modifier = Modifier
                    .padding(12.dp)
                    .size(48.dp)
                    .clickable {
                        showBar = !showBar
                    }
                    .align(Alignment.CenterVertically)
            )
        }
        Divider()
        AnimatedVisibility(visible = showBar) {
            Box(Modifier.fillMaxWidth()) {
                Row(Modifier.align(Alignment.CenterEnd)) {
                    Column(
                        Modifier.background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                    ) {
                        items.forEach {
                            modelItem(modifier = Modifier, it,{select->
                                model = select
                            }){
                                showBar = !showBar
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun modelItem(modifier: Modifier, text: String,onclick:(select:String)->Unit,selectFinish:()->Unit) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontFamily = FontFamily.Monospace,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(8.dp)
            )
            .clickable {
                onclick(text)
                selectFinish()
            }
    )
}

@Composable
@Preview
fun SwipeableCard() {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val animatedOffsetX by animateFloatAsState(targetValue = offsetX, label = "")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
            .graphicsLayer(translationX = animatedOffsetX)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount
                    // 限制滑动范围
                    offsetX = offsetX.coerceIn(-200f, 200f)
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text("Swipe me left or right")
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun CardPager() {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text("Card ${page + 1}")
            }
        }
    }
}

@Composable
fun LoginPage() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle login click */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    LoginPage()
}