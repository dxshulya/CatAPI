package com.dxshulya.catapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dxshulya.catapi.databinding.CatItemBinding
import com.dxshulya.catapi.model.Cat
import com.dxshulya.catapi.ui.main.CatFragmentDirections

class CatListAdapter: androidx.recyclerview.widget.ListAdapter<Cat, CatListAdapter.CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = CatItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class CatViewHolder(private val binding: CatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cat: Cat) {
            binding.apply {
                Glide.with(itemView).load(cat.url).into(image)
            }
            binding.image.apply {
                setOnClickListener {
                    val action = CatFragmentDirections.actionCatFragmentToShowCatFragment(catUrl = cat.url)
                    Navigation.findNavController(itemView).navigate(action)
                }
            }
        }
    }

    class CatDiffCallback : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat) =
            oldItem == newItem
    }
}