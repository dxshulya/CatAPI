package com.dxshulya.catapi.modules

import com.dxshulya.catapi.ApiService
import com.dxshulya.catapi.CatRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCatRepository(api: ApiService): CatRepository {
        return CatRepository(api)
    }
}