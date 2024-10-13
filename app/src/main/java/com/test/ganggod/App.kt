package com.test.ganggod

import android.app.Application
import com.test.ganggod.utils.AppUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
    }
}