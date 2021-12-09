package com.dxshulya.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val catRepository: CatRepository = CatRepository()

    val catList: LiveData<MutableList<Cat>>
    get() = catRepository.getCatLiveData
}