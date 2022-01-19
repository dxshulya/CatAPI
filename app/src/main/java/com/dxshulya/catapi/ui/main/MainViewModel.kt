package com.dxshulya.catapi.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dxshulya.catapi.App
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.repository.CatRepository
import javax.inject.Inject

class MainViewModel(application: Application) : AndroidViewModel(application) {

    init {
        App.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var catRepository: CatRepository

    val catList: LiveData<MutableList<Cat>>
        get() = catRepository.getCatLiveData
}