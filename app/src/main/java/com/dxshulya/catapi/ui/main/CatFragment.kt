package com.dxshulya.catapi.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dxshulya.catapi.R
import com.dxshulya.catapi.adapter.CatListAdapter
import com.dxshulya.catapi.databinding.CatListBinding
import com.dxshulya.catapi.interfaces.ToolbarBackArrow


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
        initViewModel()
        binding.swipeRefresh.setOnRefreshListener {
            initViewModel()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun initViewModel() {
        viewModel.catList.observe(viewLifecycleOwner) {
            catListAdapter.submitList(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.disableToolBar()
        return inflater.inflate(R.layout.cat_list, container, false)
    }
}
