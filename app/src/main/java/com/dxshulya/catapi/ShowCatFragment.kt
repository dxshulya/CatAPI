package com.dxshulya.catapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide

class ShowCatFragment : Fragment() {

    private lateinit var imageCat: ImageView
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.show_cat, container, false)

        imageCat = view.findViewById(R.id.show_cat)
        backButton = view.findViewById(R.id.back_button)
        backButton.apply {
            setOnClickListener {
                val transaction = activity?.supportFragmentManager?.beginTransaction()
                transaction?.replace(R.id.fragment_container, CatFragment())
                transaction?.disallowAddToBackStack()
                transaction?.commit()
            }
        }
        val url: String = arguments?.getSerializable("catURL") as String
        Glide.with(this).load(url).into(imageCat)
        return view
    }

    companion object {

        fun newInstance(url: String): ShowCatFragment {
            val args = Bundle().apply {
                putSerializable("catURL", url)
            }
            return ShowCatFragment().apply {
                arguments = args
            }
        }
    }
}