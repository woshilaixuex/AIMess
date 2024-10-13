package com.test.ganggod.page.loginPage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.ganggod.constData.UserData
import com.test.ganggod.network.login.LoginRequest
import com.test.ganggod.network.login.LoginService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginService: LoginService
):ViewModel(){
    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _error = MutableStateFlow("登陆失败")
    val error = _error.asStateFlow()

    fun login(email:String,password:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val response = loginService.login(LoginRequest(email, password))
                    if(response.code == 200){
                        _loginSuccess.value = true
                        UserData.userId = response.data.user_id
                        UserData.userName = response.data.username
                    }else{
                        _showDialog.value = true
                        Log.d("TAG", "login: ${response.msg}")
                        _error.value = response.msg
                    }
                }catch (e:Exception) {
                    _showDialog.value = true
                }
            }
        }
    }

    fun hideDialog(){
        _showDialog.value = false
    }

}