package com.elnaggar.ibtikartask

import android.app.Application
import com.elnaggar.data.di.networkModule
import com.elnaggar.ibtikartask.features.popularPeopleList.popularPeopleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(listOf(networkModule,popularPeopleModule))
        }
    }
}