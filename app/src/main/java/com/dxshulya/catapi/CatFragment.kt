package com.dxshulya.catapi

import android.os.Bundle
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

    private lateinit var image: ImageView
    private lateinit var recyclerView: RecyclerView
    private var adapter: CatAdapter? = CatAdapter(emptyList())

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)
        mainViewModel.showCat()
        mainViewModel.cat.observe(
            viewLifecycleOwner,
            { cat ->
                cat?.let {
                    Picasso.get().load(it.url).into(image)
                }
            })

        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_list, container, false)

        return view
    }



    private inner class CatHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private lateinit var cat: Cat

        fun bind(cat: Cat) {
            this.cat = cat
        }

        override fun onClick(v: View) {
        }
    }

    private inner class CatAdapter(var cats: List<Cat>)
        : RecyclerView.Adapter<CatHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : CatHolder {
            val view = layoutInflater.inflate(R.layout.cat_item, parent, false)
            return CatHolder(view)
        }

        override fun onBindViewHolder(holder: CatHolder, position: Int) {
            val cat = cats[position]
            holder.bind(cat)
        }

        override fun getItemCount() = cats.size
    }

    companion object {
        fun newInstance(): CatFragment {
            return CatFragment()
        }
    }
}
