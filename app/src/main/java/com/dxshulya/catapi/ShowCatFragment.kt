package com.dxshulya.catapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide


class ShowCatFragment : Fragment() {

    private lateinit var imageCat: ImageView
    private lateinit var cat: Cat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url: String = arguments?.getSerializable("catURL") as String
        Glide.with(this).load(url).into(imageCat)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_item, container, false)
        imageCat = view.findViewById(R.id.image)

        return view
    }

    companion object {

        fun newInstance(url: String): CatFragment {
            val args = Bundle().apply {
                putSerializable("catURL", url)
            }
            return CatFragment().apply {
                arguments = args
            }
        }
    }

}