package com.test.ganggod.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.test.ganggod.page.chatPage.ChatPage
import com.test.ganggod.page.loginPage.LoginPage
import com.test.ganggod.page.lotteryPage.LotteryPage
import com.test.ganggod.page.modelPage.ModelPage
import com.test.ganggod.page.recordPage.RecordPage
import com.test.ganggod.page.registerPage.RegisterPage

//@Composable
//fun HomeNavigationGraph(
//    navHostController: NavHostController,
//    startDestination: String = Screen.LotteryPage.route,
//){
//    NavHost(navController = navHostController, startDestination = startDestination){
//        composable(Screen.RecordPage.route){
//            RecordPage(navHostController)
//        }
//        composable(Screen.LotteryPage.route){
//            LotteryPage()
//        }
//    }
//}