package com.dxshulya.catapi.listadapter

import androidx.recyclerview.widget.DiffUtil
import com.dxshulya.catapi.model.Cat

class CatDiffCallback : DiffUtil.ItemCallback<Cat>() {

    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem == newItem
    }
}