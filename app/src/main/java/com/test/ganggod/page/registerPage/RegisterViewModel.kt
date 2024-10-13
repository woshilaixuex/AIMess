package com.test.ganggod.page.registerPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.ganggod.constData.UserData
import com.test.ganggod.network.register.RegisterRequest
import com.test.ganggod.network.register.RegisterService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerService: RegisterService
):ViewModel(){
    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess = _registerSuccess.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()


    fun register(username:String,email:String,password:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = registerService.register(RegisterRequest(username,email,password))
                if (response.code == 200) {
                    _registerSuccess.value = true
                    UserData.userId = response.data.user_id
                    UserData.userName = response.data.username
                }else{
                    _showDialog.value = true
                    _error.value = response.message
                }
            }
        }
    }

    fun hideDialog(){
        _showDialog.value = false
    }

    fun putError(){
        _error.value = "输入不能为空"
        _showDialog.value = true
    }
}