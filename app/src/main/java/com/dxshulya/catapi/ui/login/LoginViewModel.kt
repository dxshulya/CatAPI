package com.dxshulya.catapi.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.model.Authorization
import javax.inject.Inject

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private var _email = MutableLiveData<String>()
    private var _description = MutableLiveData<String>()

    val email: LiveData<String>
        get() = _email

    val description: LiveData<String>
        get() = _description

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    val loginIn: LiveData<Authorization>
        get() = catRepository.postLoginInLiveData

    fun getEmail(email: String) {
        _email.value = email
    }

    fun getDescription(description: String) {
        _description.value = description
    }
}