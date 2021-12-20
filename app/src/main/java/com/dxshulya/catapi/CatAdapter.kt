package com.dxshulya.catapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dxshulya.catapi.model.Cat

class CatAdapter (var context: Context, var catList: MutableList<Cat>):
        RecyclerView.Adapter<CatAdapter.CatHolder>() {

            inner class CatHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

                private lateinit var cat: Cat
                var imageCat: ImageView = itemView.findViewById(R.id.image)
                private var backButton: IBackButton? = null

                init {
                    imageCat.apply {
                        setOnClickListener {
                            backButton = context as IBackButton?
                            backButton?.onCatSelected(cat.url)
                        }
                    }
                }

                fun bind(cat: Cat) {
                    this.cat = cat
                    val url = cat.url
                    Glide.with(context).load(url).into(imageCat)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        return CatHolder(
            LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val cat = catList[position]
        holder.bind(cat)
    }

    override fun getItemCount(): Int {
        return catList.size
    }
}