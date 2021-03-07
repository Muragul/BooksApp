package com.example.booksapp

import android.app.Application
import com.example.booksapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            val appModule = listOf(
                networkModule,
                viewModelModule,
                useCaseModule,
                repositoryModule,
                dataBaseModule
            )
            androidContext(this@App)
            modules(appModule)
        }
    }
}