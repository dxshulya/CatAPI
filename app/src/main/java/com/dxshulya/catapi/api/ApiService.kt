package com.dxshulya.catapi.api

import com.dxshulya.catapi.model.Cat
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiService {
    @GET("images/search")
    fun getCatList(
        @Query("limit") amountOfCats: String,
    ): Observable<MutableList<Cat>>
}