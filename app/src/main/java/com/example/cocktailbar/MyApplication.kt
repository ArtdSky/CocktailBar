package com.example.cocktailbar

import android.app.Application
import com.example.cocktailbar.presentation.di.DependencyInjection
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(DependencyInjection))
        }
    }
}