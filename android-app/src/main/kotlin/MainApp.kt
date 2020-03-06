package io.thumbcat.oss.gtcompanion.android

import android.app.Application
import android.content.Context
import io.thumbcat.oss.gtcompanion.di.initKoin
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            modules(module { single<Context> { this@MainApp } })
        }
    }
}
