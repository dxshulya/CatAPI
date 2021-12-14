package com.dxshulya.catapi.RxJava2

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
object RetrofitClient {
    private var retrofit: Retrofit? = null

    fun getRetrofitClient(baseUrl: String) : Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        @Singleton
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header("Authorization", "f6880836-42d2-4988-b97b-e87481d59352")
            val request = requestBuilder.build()
            chain.proceed(request)
        }

        @Singleton
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .readTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .connectTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .writeTimeout((60*2).toLong(), TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

            if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit
    }
}