package com.dxshulya.catapi

import android.app.Application
import com.dxshulya.catapi.modules.NetModule
import com.dxshulya.catapi.modules.RepositoryModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule("https://api.thecatapi.com/v1/", "f6880836-42d2-4988-b97b-e87481d59352", this))
            .repositoryModule(RepositoryModule())
            .build()
    }

    companion object {
        private lateinit var app: App
        @Synchronized
        fun getInstance(): App {
            return app
        }
    }
}