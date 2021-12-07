package com.dxshulya.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _cat = MutableLiveData<Cat>()
    val cat: LiveData<Cat> = _cat


    fun showCat() {
        val client = ApiConfig.getApiService().getCatList()
        client.enqueue(object : Callback<MutableList<Cat>> {
            override fun onResponse(
                call: Call<MutableList<Cat>>,
                response: Response<MutableList<Cat>>
            ) {
                if (response.isSuccessful) {
                    //_cat.postValue(response.body()?.firstOrNull())
                    _cat.value = response.body()?.firstOrNull()
                }
            }

            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {
            }
        })
    }
}