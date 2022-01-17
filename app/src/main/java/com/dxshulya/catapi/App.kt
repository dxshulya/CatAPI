package com.dxshulya.catapi

import android.app.Application
import android.util.Log
import com.dxshulya.catapi.modules.NetModule
import com.dxshulya.catapi.modules.RepositoryModule
import com.dxshulya.catapi.ui.apikey.ApiKeyViewModel

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        app = this

        var sharedPreference = SharedPreference(applicationContext)

        appComponent = DaggerAppComponent.builder()
            .netModule(NetModule("https://api.thecatapi.com/v1/", sharedPreference.apikey, this))
            .repositoryModule(RepositoryModule())
            .build()

        Log.e("АпиКлюч из Арр", "" + sharedPreference.apikey)
    }

    companion object {
        private lateinit var app: App

        @Synchronized
        fun getInstance(): App {
            return app
        }
    }
}