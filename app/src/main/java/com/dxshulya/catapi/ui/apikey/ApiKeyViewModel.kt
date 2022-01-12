package com.dxshulya.catapi.ui.apikey

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.SharedPreferenceRepository
import com.dxshulya.catapi.databinding.FragmentApiKeyBinding
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.Cat
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class ApiKeyViewModel (application: Application) : AndroidViewModel(application) {

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

//    private var _getApiKeyLiveData = MutableLiveData<Authorization>()
//    val getApiKeyLiveData: LiveData<Authorization>
//        get() = _getApiKeyLiveData


    val getApikey: LiveData<Authorization>
        get() = catRepository.getApiKeyLiveData

    /*fun getRequest() {
        catRepository.getApiKey(sharedPreferenceRepository.apikey)
            .subscribe({
                _getApiKeyLiveData.value = it
            }, {
                if (it is HttpException) {
                    val body = it.response()?.errorBody()
                    val gson = Gson()
                    val adapter: TypeAdapter<Authorization> =
                        gson.getAdapter(Authorization::class.java)
                    val error: Authorization = adapter.fromJson(body?.string())
                    _getApiKeyLiveData.value = error
                }
            })
    }*/


    fun updateApiKey(apikey: String) {
        sharedPreferenceRepository.apikey = apikey
    }


}