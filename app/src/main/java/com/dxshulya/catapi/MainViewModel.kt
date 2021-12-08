package com.dxshulya.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val catRepository: CatRepository = CatRepository()

    val catList: LiveData<MutableList<Cat>>
    get() = catRepository.getCatLiveData
}