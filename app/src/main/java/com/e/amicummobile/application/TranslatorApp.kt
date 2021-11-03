package com.e.amicummobile.application

import android.app.Application
import com.e.amicummobile.di.application
import com.e.amicummobile.di.mainScreen
import org.koin.core.context.GlobalContext.startKoin

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}