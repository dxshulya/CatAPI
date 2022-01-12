package com.dxshulya.catapi.modules

import com.dxshulya.catapi.api.ApiService
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