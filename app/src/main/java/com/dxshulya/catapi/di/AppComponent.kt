package com.dxshulya.catapi.di

import com.dxshulya.catapi.datastore.SharedPreference
import com.dxshulya.catapi.modules.NetModule
import com.dxshulya.catapi.modules.RepositoryModule
import com.dxshulya.catapi.repository.CatRepository
import com.dxshulya.catapi.ui.apikey.ApiKeyViewModel
import com.dxshulya.catapi.ui.login.LoginViewModel
import com.dxshulya.catapi.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (RepositoryModule::class)])
interface AppComponent {
    fun inject(catRepository: CatRepository)
    fun inject(mainViewModel: MainViewModel)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(apiKeyViewModel: ApiKeyViewModel)
    fun inject(sharedPreference: SharedPreference)
}