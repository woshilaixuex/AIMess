package com.test.ganggod.page.chatPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.ganggod.R
import com.test.ganggod.ui.theme.LightModeColor

@Composable
fun TopBar(currentModel: String, onModelChange: (String) -> Unit,onBack:()->Unit) {
    val iconSize = 36.dp
    var showBar by remember {
        mutableStateOf(false)
    }

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
                .statusBarsPadding()
        ) {
            IconButton(
                onClick = { onBack() },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    painterResource(R.drawable.back),
                    contentDescription = "back",
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.width(48.dp))

        }
    }
}

@Composable
fun ModelItem(modifier: Modifier, text: String, onclick:(select:String)->Unit, selectFinish:()->Unit) {
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