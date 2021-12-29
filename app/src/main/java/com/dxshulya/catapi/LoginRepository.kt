package com.dxshulya.catapi

import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.model.Authorization
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginRepository (private val api: ApiService?) {

    fun getFavourites(apikey: String): @NonNull Observable<List<Authorization>> {
        return api?.getApiKey(apikey)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread()) as @NonNull Observable<List<Authorization>>
    }
}