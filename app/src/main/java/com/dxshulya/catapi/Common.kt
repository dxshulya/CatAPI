package com.dxshulya.catapi

object Common {
    private val BASE_URL = "https://api.thecatapi.com/v1/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}