package com.dxshulya.catapi.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.SharedPreferenceRepository
import com.dxshulya.catapi.databinding.FragmentLoginBinding
import com.dxshulya.catapi.model.Authorization
import com.dxshulya.catapi.model.User
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel (application: Application) : AndroidViewModel(application) {

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    @Inject
    lateinit var sharedPreferenceRepository: SharedPreferenceRepository

    private var _loginInLiveData = MutableLiveData<Authorization>()
    val loginInLiveData: LiveData<Authorization>
        get() = _loginInLiveData

    fun postRequest() {
        val user = User(sharedPreferenceRepository.email, sharedPreferenceRepository.description)
        catRepository.postLoginIn(user)
            .subscribe({
                _loginInLiveData.value = it
            }, {
                if (it is HttpException) {
                    val body = it.response()?.errorBody()
                    val gson = Gson()
                    val adapter: TypeAdapter<Authorization> =
                        gson.getAdapter(Authorization::class.java)
                        val error: Authorization = adapter.fromJson(body?.string())
                        _loginInLiveData.value = error
                }
            })
    }
    fun updateEmail(email: String) {
        sharedPreferenceRepository.email = email
    }
    fun updateDescription(description: String) {
        sharedPreferenceRepository.description = description
    }
}