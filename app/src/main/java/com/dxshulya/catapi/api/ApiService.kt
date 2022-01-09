package com.dxshulya.catapi.api

import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.model.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiService {
    @GET("images/search")
    fun getCatList(
        @Query("limit") amountOfCats: String,
    ): Observable<MutableList<Cat>>

    @POST("user/passwordlesssignup")
    fun loginIn(
        @Body body: User
    ): Observable<Authorization>

    @GET("favourites")
    fun getApiKey(
        @Query("api_key") apiKey: String,
    ): Observable<List<Authorization>>
}