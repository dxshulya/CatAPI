package com.dxshulya.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxshulya.catapi.databinding.ActivityMainBinding
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.ui.CatFragment
import com.dxshulya.catapi.ui.ShowCatFragment

class MainActivity : AppCompatActivity(), ISelectCat {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

        if(currentFragment == null) {
            val fragment = CatFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCatSelected(url: String) {
        val fragment = ShowCatFragment.newInstance(url)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}