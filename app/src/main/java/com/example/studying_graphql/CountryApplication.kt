package com.example.studying_graphql

import android.app.Application
import com.example.studying_graphql.data.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CountryApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CountryApplication)
            modules(module)
        }
    }
}