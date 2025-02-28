package com.rainday.datastorecmp

import android.app.Application
import org.koin.android.ext.koin.androidContext

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            config = {
                androidContext(this@BaseApplication)
            }
        )
    }
}