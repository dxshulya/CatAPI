package com.dxshulya.catapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import retrofit2.*

class CatFragment : Fragment() {

    private lateinit var image: ImageView

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)

        mainViewModel.cat.observe(
            viewLifecycleOwner,
            { cat ->
                cat?.let {
                    Picasso.get().load(it.url).into(image)
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cat_picture, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CatFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
