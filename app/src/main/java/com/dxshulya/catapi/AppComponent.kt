package com.dxshulya.catapi

import com.dxshulya.catapi.modules.NetModule
import com.dxshulya.catapi.modules.RepositoryModule
import com.dxshulya.catapi.ui.main.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (RepositoryModule::class)])
interface AppComponent {
    fun inject(catRepository: CatRepository)
    fun inject(mainViewModel: MainViewModel)
}