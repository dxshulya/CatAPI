package com.dxshulya.catapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _cat = MutableLiveData<Cat>()
    val cat: LiveData<Cat> = _cat
    val repository = CatRepository()

    init {
        repository.getCat {
            _cat.value = it
        }
    }
}