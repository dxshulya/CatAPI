package com.dxshulya.catapi

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.api.ApiService
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.model.User
import com.dxshulya.catapi.ui.apikey.ApiKeyViewModel
import com.dxshulya.catapi.ui.login.LoginViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

const val AMOUNT_OF_CATS = "10"
private val loginViewModel: LoginViewModel = LoginViewModel(Application())
private val apiKeyViewModel: ApiKeyViewModel = ApiKeyViewModel(Application())

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
                    Log.e("Ошибка!", it.message.toString())
                })
            return data
        }

    val getApiKeyLiveData: MutableLiveData<Authorization>
       get() {
            val data: MutableLiveData<Authorization> = MutableLiveData<Authorization>()
            api.getApiKey(apiKeyViewModel.apikey.value.toString())
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
               }, {
                  Log.e("Ошибка", it.message.toString())
                })
            return data
        }

    val postLoginInLiveData: MutableLiveData<Authorization>
        get() {
            val data: MutableLiveData<Authorization> = MutableLiveData<Authorization>()
            api.loginIn(body = User(loginViewModel.email.value.toString(), loginViewModel.description.value.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it
                }, {
                    Log.e("Ошибка!", it.message.toString())
                })
            return data
        }
}