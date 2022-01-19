package com.dxshulya.catapi.modules

import android.content.Context
import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.datastore.SharedPreference
import com.dxshulya.catapi.repository.CatRepository
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
    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreference {
        return SharedPreference(context)
    }
}