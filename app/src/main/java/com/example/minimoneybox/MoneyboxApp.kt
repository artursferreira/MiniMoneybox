package com.example.minimoneybox

import android.app.Application
import com.example.minimoneybox.di.appModule
import com.example.minimoneybox.di.loginModule
import com.example.minimoneybox.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoneyboxApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoneyboxApp)
            modules(listOf(appModule, networkModule, loginModule))
        }
    }
}