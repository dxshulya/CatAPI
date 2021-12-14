package com.dxshulya.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dxshulya.catapi.RxJava2.CatRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val catRepository: CatRepository = CatRepository()
) : ViewModel() {

    val catList: LiveData<MutableList<Cat>>
        get() = catRepository.getCatLiveData
}