package com.dxshulya.catapi.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.SharedPreference
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.User
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        var email = ""
        var description = ""
    }

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    private var _loginData = MutableLiveData<Authorization>()
    val loginData: LiveData<Authorization>
        get() = _loginData

    fun postLoginIn() {
        val user = User(email, description)
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

    fun getEmail(newEmail: String) {
        email = newEmail
    }

    fun getDescription(newDescription: String) {
        description = newDescription
    }
}