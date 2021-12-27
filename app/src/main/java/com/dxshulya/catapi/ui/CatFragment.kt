package com.dxshulya.catapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxshulya.catapi.R
import com.dxshulya.catapi.ToolbarBackArrow
import com.dxshulya.catapi.adapter.CatListAdapter
import com.dxshulya.catapi.databinding.CatListBinding

class CatFragment : Fragment(R.layout.cat_list), ToolbarBackArrow {

    private val viewModel: MainViewModel by viewModels()
    private val catListAdapter = CatListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val binding = CatListBinding.bind(view)

            binding.apply {
                recyclerView.apply {
                    adapter = catListAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                    setHasFixedSize(true)
                }
            }
            viewModel.catList.observe(viewLifecycleOwner) {
                catListAdapter.submitList(it)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.disableToolBar()

        val view = inflater.inflate(R.layout.cat_list, container, false)
        return view
    }
}
