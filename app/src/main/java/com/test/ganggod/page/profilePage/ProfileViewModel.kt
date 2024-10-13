package com.test.ganggod.page.profilePage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.ganggod.constData.UserData
import com.test.ganggod.network.profile.AiModelsRequest
import com.test.ganggod.network.profile.ProfileService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileService: ProfileService
) : ViewModel(){
    private val _userName = MutableStateFlow(UserData.userName)
    val userName = _userName.asStateFlow()

    private val _credit = MutableStateFlow(UserData.credit)
    val credit = _credit.asStateFlow()

    private val _model1 = MutableStateFlow(UserData.modelTimes1)
    val model1 = _model1.asStateFlow()

    private val _model2 = MutableStateFlow(UserData.modelTimes2)
    val model2 = _model2.asStateFlow()

    private val _model3 = MutableStateFlow(UserData.modelTimes3)
    val model3 = _model3.asStateFlow()

    init {
        UserData
        _userName.value = UserData.userName
        getUserProfile()
        getAiModelsTimes()
    }

    private fun getUserProfile(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val response = profileService.getProfile(UserData.userId)
                    _credit.value = response.data.credit
                    UserData.credit = response.data.credit
                }catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getAiModelsTimes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val response = profileService.getAiModels(AiModelsRequest(UserData.userId))
                _model1.value = response.data.ai_infos[0].query_count
                _model2.value = response.data.ai_infos[1].query_count
                _model3.value = response.data.ai_infos[2].query_count
                UserData.modelTimes1 = _model1.value
                UserData.modelTimes2 = _model2.value
                UserData.modelTimes3 = _model3.value
            }
        }
    }
}