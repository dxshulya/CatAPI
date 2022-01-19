package com.dxshulya.catapi.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dxshulya.catapi.R
import com.dxshulya.catapi.interfaces.ToolbarBackArrow

class ShowCatFragment : Fragment(), ToolbarBackArrow {

    private lateinit var imageCat: ImageView
    private val args: ShowCatFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableToolBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return this.findNavController().navigateUp()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_cat, container, false)

        imageCat = view.findViewById(R.id.show_cat)
        val catUrl = args.catUrl

        Glide.with(this).load(catUrl).into(imageCat)

        return view
    }
}