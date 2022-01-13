package com.dxshulya.catapi.ui.apikey

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.model.Authorization
import javax.inject.Inject

class ApiKeyViewModel (application: Application) : AndroidViewModel(application) {

    companion object {
        var apikey = ""
    }

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    val apiKey: LiveData<Authorization>
        get() = catRepository.getApiKeyLiveData

    fun getApiKey(newApiKey: String) {
        apikey = newApiKey
    }
}