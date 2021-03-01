package com.example.booksapp

import android.app.Application
import com.example.booksapp.di.networkModule
import com.example.booksapp.di.repositoryModule
import com.example.booksapp.di.useCaseModule
import com.example.booksapp.di.viewModelModule
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
                repositoryModule
            )
            androidContext(this@App)
            modules(appModule)
        }
    }
}