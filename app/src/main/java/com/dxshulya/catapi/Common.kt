package com.dxshulya.catapi

object Common {
private const val BASE_URL = "https://api.thecatapi.com/v1/"

    val getApiService: ApiService
    get() = RetrofitClient.getRetrofitClient(BASE_URL).create(ApiService::class.java)
}