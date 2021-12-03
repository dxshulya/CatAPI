package com.dxshulya.catapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class API {
    companion object {
        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.thecatapi.com/v1/")
                    .build()
            }
            return retrofit
        }
        private var retrofit: Retrofit? = null
    }
}

interface APIInterface {
    @GET("images/search?api_key=f6880836-42d2-4988-b97b-e87481d59352")
    fun getCats() : Call<List<Cat>>
}