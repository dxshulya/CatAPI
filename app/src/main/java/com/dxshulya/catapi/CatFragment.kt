package com.dxshulya.catapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CatFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var adapter: CatAdapter? = null

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_list, container, false)

        mainViewModel!!.catList.observe(viewLifecycleOwner, { cats ->
            Log.e("CatFragment", "cats" + cats[0].id)
        })

        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = adapter

        mainViewModel.catList.observe( viewLifecycleOwner, { cats ->
            Log.e("CatFragment", "cats " + cats[0].url)

            adapter = CatAdapter(cats)
            adapter!!.notifyDataSetChanged()
            recyclerView!!.adapter = adapter
        })

        return view
    }

    inner class CatHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private lateinit var cat: Cat
        var imageCat: ImageView = itemView.findViewById(R.id.image)

        fun bind(cat: Cat) {
            this.cat = cat
            var url = cat.url
            Picasso.get().load(url).into(imageCat)
        }
    }

    private inner class CatAdapter(var catList: MutableList<Cat>)
        : RecyclerView.Adapter<CatHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
            val view = layoutInflater.inflate(R.layout.cat_item, parent, false)
            return CatHolder(view)
        }

        override fun onBindViewHolder(holder: CatHolder, position: Int) {
            val cat = catList[position]
            holder.bind(cat)
        }

        override fun getItemCount(): Int {
            return catList.size
        }
    }

    companion object {
        fun newInstance(): CatFragment {
            return CatFragment()
        }
    }
}
