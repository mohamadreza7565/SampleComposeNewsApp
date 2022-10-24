package com.mra.newsappcompose.core

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.mra.newsappcompose.core.di.gsonModule
import com.mra.newsappcompose.core.di.networkModule
import com.mra.newsappcompose.core.di.repositoryModule
import com.mra.newsappcompose.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(gsonModule,repositoryModule, networkModule, viewModelModule))
        }

    }

}