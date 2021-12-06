package com.dxshulya.catapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatRepository {

    fun getCat(callback: (Cat?) -> Unit) {

        val client = ApiConfig.getApiService().getCatList()
        client.enqueue(object : Callback<MutableList<Cat>> {
            override fun onResponse(
                call: Call<MutableList<Cat>>,
                response: Response<MutableList<Cat>>
            ) {
                if (response.isSuccessful) {

                    callback.invoke(response.body()?.firstOrNull())

                } else {
                    Log.e("MainViewModel", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {
                Log.e("sadf", "repos", t)
            }
        })
    }
}