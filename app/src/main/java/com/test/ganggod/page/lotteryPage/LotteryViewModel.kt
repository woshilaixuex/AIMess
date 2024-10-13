package com.test.ganggod.page.lotteryPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.ganggod.constData.UserData
import com.test.ganggod.network.lottery.LotteryRequest
import com.test.ganggod.network.lottery.LotteryService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LotteryViewModel @Inject constructor(
    private val lotteryService: LotteryService
) :ViewModel() {
    private val _isRolling = MutableStateFlow(0)
    val isRolling = _isRolling.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _lotteryResult = MutableStateFlow("")
    val lotteryResult = _lotteryResult.asStateFlow()

    fun startLottery(){
        viewModelScope.launch {
            _isRolling.value = 1
            withContext(Dispatchers.IO){
                try {
                    delay(3000)
                    val response = lotteryService.lottery(LotteryRequest(UserData.userId, 100001))
                    _isRolling.value = 2
                    delay(1000)
                    _lotteryResult.value = response.data.award_title
                    _showDialog.value = true
                }catch (e:Exception){
                    _isRolling.value = 3
                    _lotteryResult.value = "积分不足"
                    _showDialog.value = true
                }
            }
        }
    }

    fun hideDialog(){
        _isRolling.value = 0
        _lotteryResult.value = ""
        _showDialog.value = false
    }
}