package com.dxshulya.catapi.ui.apikey

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.model.Authorization
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class ApiKeyViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        var apikey = ""
    }

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    private var _apikeyData = MutableLiveData<Boolean>()
    val apikeyData: LiveData<Boolean>
        get() = _apikeyData

    private var _eApiKeyData = MutableLiveData<Authorization>()
    val eApiKeyData: LiveData<Authorization>
        get() = _eApiKeyData

    fun getApiKey() {
        catRepository.getApiKey(apikey)
            .subscribe({
                _apikeyData.postValue(true)
            }, {
                _apikeyData.postValue(false)
                if (it is HttpException) {
                    val body = it.response()?.errorBody()
                    val gson = Gson()
                    val adapter: TypeAdapter<Authorization> =
                        gson.getAdapter(Authorization::class.java)
                    val error: Authorization =
                        adapter.fromJson(body?.string())
                    _eApiKeyData.value = error
                }
            })
    }

    fun getApiKey(newApiKey: String) {
        apikey = newApiKey
    }
}