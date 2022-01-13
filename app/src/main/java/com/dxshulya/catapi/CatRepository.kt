package com.dxshulya.catapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.model.User
import com.dxshulya.catapi.ui.apikey.ApiKeyViewModel
import com.dxshulya.catapi.ui.login.LoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

const val AMOUNT_OF_CATS = "10"

class CatRepository(private val api: ApiService) {

    val getCatLiveData: MutableLiveData<MutableList<Cat>>
        get() {
            val data: MutableLiveData<MutableList<Cat>> = MutableLiveData<MutableList<Cat>>()
            api.getCatList(AMOUNT_OF_CATS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, {
                    Log.e("Список котов", it.message.toString())
                })
            return data
        }

    val getApiKeyLiveData: MutableLiveData<Authorization>
       get() {
            val data: MutableLiveData<Authorization> = MutableLiveData<Authorization>()
            api.getApiKey(ApiKeyViewModel.apikey)
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
               }, {
                  Log.e("АПИКЛЮЧ", it.message.toString())
                })
            return data
        }

    val postLoginInLiveData: MutableLiveData<Authorization>
        get() {
            val data: MutableLiveData<Authorization> = MutableLiveData<Authorization>()
            api.loginIn(User(LoginViewModel.email, LoginViewModel.description))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, {
                    Log.e("Авторизация", it.message.toString())
                })
            return data
        }
}