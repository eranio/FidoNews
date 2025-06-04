package com.eranio.fidoapp

import android.app.Application
import appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class FidoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FidoApplication)
            modules(appModule)
        }
    }
}