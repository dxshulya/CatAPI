package com.dxshulya.catapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.model.Cat
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

const val AMOUNT_OF_CATS = "10"

class CatRepository(private val api: ApiService?) {
    val getCatLiveData: MutableLiveData<MutableList<Cat>>
        get() {
            val data: MutableLiveData<MutableList<Cat>> = MutableLiveData<MutableList<Cat>>()
            api?.getCatList(AMOUNT_OF_CATS)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    data.value = it
                }, {
                    Log.e("error", it.message.toString())
                })
            return data
        }
}