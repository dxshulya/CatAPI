package com.dxshulya.catapi

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatRepository {
    private val apiService: ApiService? = Common.getApiService
    private val compositeDisposable = CompositeDisposable()

    val getCatLiveData: MutableLiveData<MutableList<Cat>>
    get() {
        val data: MutableLiveData<MutableList<Cat>> = MutableLiveData<MutableList<Cat>>()
        apiService?.getCatList("10")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.let { it ->
                compositeDisposable.add(
                it
                    .subscribe{
                        if (it != null) {
                            data.value = it
                        }
                    })
            }
        return data
    }
}