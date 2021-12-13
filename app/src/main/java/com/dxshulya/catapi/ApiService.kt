package com.dxshulya.catapi

import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @GET("images/search")
    fun getCatList(
        @Query("limit") amountOfCats: String,
    ): Observable<MutableList<Cat>>
}