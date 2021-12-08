package com.dxshulya.catapi

import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    //@GET("images/search?api_key=f6880836-42d2-4988-b97b-e87481d59352")
    @GET("images/search")
    fun getCatList(
        @Query("limit") amountOfCats: String,
    ): Observable<MutableList<Cat>>
}