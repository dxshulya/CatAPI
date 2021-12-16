package com.dxshulya.catapi

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule (private val app: App) {

    val BASE_URL = "https://api.thecatapi.com/v1/"

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCatRepository(apiService: ApiService): CatRepository {
        return CatRepository(apiService)
    }

    @Provides
    fun provideCatViewModel(catRepository: CatRepository): MainViewModel {
        return MainViewModel(catRepository)
    }
}