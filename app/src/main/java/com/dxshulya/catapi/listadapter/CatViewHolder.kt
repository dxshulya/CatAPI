package com.dxshulya.catapi.listadapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dxshulya.catapi.R
import com.dxshulya.catapi.model.Cat

class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageCat: ImageView = itemView.findViewById(R.id.image)
    private lateinit var cat: Cat

    fun bind(cat : Cat) {
        this.cat = cat
        val url = cat.url
        Glide.with(itemView).load(url).into(imageCat)
    }
}