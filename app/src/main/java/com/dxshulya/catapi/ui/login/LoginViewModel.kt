package com.dxshulya.catapi.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.CatRepository
import com.dxshulya.catapi.model.Authorization
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

    val loginIn: LiveData<Authorization>
        get() = catRepository.postLoginInLiveData

    fun getEmail(newEmail: String) {
        email = newEmail
    }

    fun getDescription(newDescription: String) {
        description = newDescription
    }
}