package com.dxshulya.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dxshulya.catapi.ui.CatFragment
import com.dxshulya.catapi.ui.ShowCatFragment

const val AMOUNT_OF_CATS = "10"

class MainActivity : AppCompatActivity(), IBackButton {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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