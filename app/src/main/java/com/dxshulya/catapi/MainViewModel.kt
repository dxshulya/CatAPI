package com.dxshulya.catapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel
@Inject constructor(
  private val catRepository: CatRepository
) : ViewModel() {

    //private val catRepository: CatRepository = CatRepository()

    val catList: LiveData<MutableList<Cat>>
        get() = catRepository.getCatLiveData
}