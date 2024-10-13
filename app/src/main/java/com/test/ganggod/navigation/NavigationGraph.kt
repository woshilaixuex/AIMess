package com.test.ganggod.navigation

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.test.ganggod.R
import com.test.ganggod.page.chatPage.ChatPage
import com.test.ganggod.page.loginPage.LoginPage
import com.test.ganggod.page.lotteryPage.LotteryPage
import com.test.ganggod.page.modelPage.ModelPage
import com.test.ganggod.page.profilePage.ProfilePage
import com.test.ganggod.page.recordPage.RecordPage
import com.test.ganggod.page.registerPage.RegisterPage

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
    startDestination: String = Screen.LoginPage.route,
){
    var select by remember {  mutableIntStateOf(0)}
    var isNavBarVis by remember { mutableStateOf(false) }
    Column (modifier = Modifier.navigationBarsPadding()){
        NavHost(navController = navHostController, startDestination = startDestination, modifier = Modifier.weight(1f)){
            composable(Screen.RecordPage.route){
                RecordPage(navHostController, hideNavBar = {isNavBarVis = false;Log.d("TAG", "RecordPage: $isNavBarVis")}, showNavBar = {isNavBarVis = true})
            }
            composable(Screen.ModelPage.route){
                ModelPage(navHostController)
            }
            composable(Screen.LotteryPage.route){
                LotteryPage()
            }
            composable(
                route = Screen.ChatPage.route + "/{id}",
                arguments = listOf(navArgument("id"){type = NavType.IntType})
            ) {
                val chatId = it.arguments?.getInt("id")?:1
                ChatPage(navHostController, chatId)
            }
            composable(Screen.LoginPage.route){
                LoginPage(navHostController)
            }
            composable(Screen.RegisterPage.route){
                RegisterPage(navHostController)
            }
            composable(Screen.ProfilePage.route){
                ProfilePage(navHostController) {
                    isNavBarVis = false
                }
            }
        }
        if (isNavBarVis) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Icon(
                        painterResource(id = if (select == 0) R.drawable.chat_selected else R.drawable.chat_unselected),
                        contentDescription = "Previous Level",
                        modifier = Modifier
                            .size(48.dp).clickable {
                                if (select != 0) {
                                    select = 0
                                    navHostController.navigate(Screen.RecordPage.route) {
                                        popUpTo(Screen.ProfilePage.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            },
                        tint = Color.Unspecified
                    )
                    Text(text = "聊天", fontSize = 20.sp)
                }
                Column {
                    Icon(
                        painterResource(id = if (select == 1) R.drawable.more_selected else R.drawable.more),
                        contentDescription = "我的",
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                if (select != 1) {
                                    select = 1
                                    navHostController.navigate(Screen.LotteryPage.route) {
                                        popUpTo(Screen.LotteryPage.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            },
                        tint = Color.Unspecified
                    )
                    Text(text = "抽奖", fontSize = 20.sp)
                }
                Column {
                    Icon(
                        painterResource(id = if (select == 2) R.drawable.mine_selected else R.drawable.mine),
                        contentDescription = "我的",
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                if (select != 2) {
                                    select = 2
                                    navHostController.navigate(Screen.ProfilePage.route) {
                                        popUpTo(Screen.RecordPage.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                            },
                        tint = Color.Unspecified
                    )
                    Text(text = "我的", fontSize = 20.sp)
                }
            }
        }
    }

}
