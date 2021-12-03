package com.dxshulya.catapi

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("images/search?api_key=f6880836-42d2-4988-b97b-e87481d59352")
    fun getCatList(): Call<MutableList<Cat>>
}