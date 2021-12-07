package com.dxshulya.catapi

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CatRepository {
    private val apiService: ApiService = Common.getApiService
    private val compositeDisposable = CompositeDisposable()

    val getCatLiveData: MutableLiveData<MutableList<Cat>>
    get() {
        val data: MutableLiveData<MutableList<Cat>> = MutableLiveData<MutableList<Cat>>()
        compositeDisposable.add(apiService.getCatList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ cats ->
                if (cats != null) {
                    data.value = cats
                }
            })
        return data
    }
}