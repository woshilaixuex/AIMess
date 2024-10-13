package com.test.ganggod.navigation

sealed class Screen(val route:String, val description:String){
    data object RecordPage : Screen("RecordPage","主页面")

    data object ModelPage: Screen("ModelPage","模型选择页面")

    data object ChatPage : Screen("ChatPage","聊天页面")

    data object LoginPage : Screen("LoginPage","登录页面")

    data object RegisterPage : Screen("RegisterPage","注册页面")

    data object LotteryPage:Screen("LotteryPage","抽奖页面")

    data object ProfilePage:Screen("ProfilePage","个人页面")
}