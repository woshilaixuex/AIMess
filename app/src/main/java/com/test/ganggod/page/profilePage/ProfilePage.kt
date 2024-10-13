package com.test.ganggod.page.profilePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.test.ganggod.constData.UserData
import com.test.ganggod.navigation.Screen


@Composable
fun ProfilePage(
    navHostController: NavHostController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
    onLogout: () -> Unit
) {
    val useName by profileViewModel.userName.collectAsState()
    val credit by profileViewModel.credit.collectAsState()
    val model1 by profileViewModel.model1.collectAsState()
    val model2 by profileViewModel.model2.collectAsState()
    val model3 by profileViewModel.model3.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CardDefaults.shape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "用户昵称：$useName", fontSize = MySize.textSize, modifier = Modifier
                        .padding(MySize.ItemPadding)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Card(shape = CardDefaults.shape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "积分：$credit", fontSize = MySize.textSize, modifier = Modifier
                        .padding(MySize.ItemPadding)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(shape = CardDefaults.shape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "GPT-4：$model1", fontSize = MySize.textSize, modifier = Modifier
                        .padding(MySize.ItemPadding)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(shape = CardDefaults.shape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "Dalle-E-2：$model2", fontSize = MySize.textSize, modifier = Modifier
                        .padding(MySize.ItemPadding)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(shape = CardDefaults.shape) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = "Dalle-E-3：$model3", fontSize = MySize.textSize, modifier = Modifier
                        .padding(MySize.ItemPadding)
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            UserData.resetUserData()
            onLogout() // 注销状态
            navHostController.navigate(Screen.LoginPage.route) {
                popUpTo(Screen.ProfilePage.route) {
                    inclusive = true
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "注销")
        }
    }
}


object MySize {
    val photoSize = 72.dp
    val nameSize = 28.sp
    val iconSize = 48.dp
    val textSize = 20.sp
    val ItemPadding = 20.dp
    val photoPadding = 25.dp
}