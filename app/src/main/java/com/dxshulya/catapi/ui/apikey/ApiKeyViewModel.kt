package com.dxshulya.catapi.ui.apikey

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.datastore.SharedPreference
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.repository.CatRepository
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class ApiKeyViewModel(application: Application) : AndroidViewModel(application) {

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    @Inject
    lateinit var sharedPreference: SharedPreference

    private var _apikeyData = MutableLiveData<Boolean>()
    val apikeyData: LiveData<Boolean>
        get() = _apikeyData

    private var _eApiKeyData = MutableLiveData<Authorization>()
    val eApiKeyData: LiveData<Authorization>
        get() = _eApiKeyData

    fun checkKeyStatus(): Boolean {
        return _eApiKeyData.value!!.status == 401
    }

    fun getApiKeyRequest() {
        catRepository.getApiKey(sharedPreference.apikey)
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

    fun updateApiKey(apiKey: String) {
        sharedPreference.apikey = apiKey
    }
}