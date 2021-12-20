package com.dxshulya.catapi.modules

import com.dxshulya.catapi.ApiService
import com.dxshulya.catapi.KeyInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule (private val baseUrl: String, private val key: String) {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.readTimeout((60*2).toLong(), TimeUnit.SECONDS)
        okHttpClient.connectTimeout((60*2).toLong(), TimeUnit.SECONDS)
        okHttpClient.writeTimeout((60*2).toLong(), TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor(KeyInterceptor(key))
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }
}