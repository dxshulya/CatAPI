package com.dxshulya.catapi.ui.apikey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dxshulya.catapi.R
import com.dxshulya.catapi.ui.login.LoginFragmentDirections
import com.dxshulya.catapi.ui.main.CatFragmentDirections

class ApiKeyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_key, container, false)



        return view
    }
}