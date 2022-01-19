package com.dxshulya.catapi.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.datastore.SharedPreference
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.User
import com.dxshulya.catapi.repository.CatRepository
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    @Inject
    lateinit var sharedPreference: SharedPreference

    private var _loginData = MutableLiveData<Authorization>()
    val loginData: LiveData<Authorization>
        get() = _loginData

    fun checkBadRequest() {

    }


    fun postLoginInRequest() {
        val user = User(sharedPreference.email, sharedPreference.description)
        catRepository.postLoginIn(user)
            .subscribe({
                _loginData.value = it
            }, {
                if (it is HttpException) {
                    val body = it.response()?.errorBody()
                    val gson = Gson()
                    val adapter: TypeAdapter<Authorization> =
                        gson.getAdapter(Authorization::class.java)
                    val error: Authorization = adapter.fromJson(body?.string())
                    _loginData.value = error
                }
            })
    }

    fun updateEmail(email: String) {
        sharedPreference.email = email
    }

    fun updateDescription(description: String) {
        sharedPreference.description = description
    }
}