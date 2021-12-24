package com.dxshulya.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxshulya.catapi.databinding.ActivityMainBinding
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.ui.CatFragment
import com.dxshulya.catapi.ui.ShowCatFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}