package com.dxshulya.catapi

import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.model.Cat
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
const val AMOUNT_OF_CATS = "10"

class CatRepository (private val api: ApiService?) {
    private val compositeDisposable = CompositeDisposable()
    val getCatLiveData: MutableLiveData<MutableList<Cat>>
        get() {
            val data: MutableLiveData<MutableList<Cat>> = MutableLiveData<MutableList<Cat>>()
            api?.getCatList(AMOUNT_OF_CATS)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())?.let { it ->
                    compositeDisposable.add(
                        it
                            .subscribe{ it?.let {
                                data.value = it
                            }
                            })
                }
            return data
        }
}