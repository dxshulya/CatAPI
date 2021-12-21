package com.dxshulya.catapi.listadapter

import android.widget.ListAdapter
import com.dxshulya.catapi.model.Cat


class CatListAdapter : ListAdapter<Cat, CatViewHolder>(CatDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val itemViewHolder = ItemViewHolder(view)
        return itemViewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}
