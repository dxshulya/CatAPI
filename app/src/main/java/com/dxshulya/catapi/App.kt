package com.dxshulya.catapi

import android.app.Application

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.component.inject(this)
    }
}