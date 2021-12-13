package com.dxshulya.catapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable
import android.os.Parcelable

class CatFragment : Fragment() {

    private var callbacks: ICallbacks? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: CatAdapter? = null

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*if (savedInstanceState != null) {
            val savedRecyclerLayoutState: Parcelable? =
                savedInstanceState.getParcelable("save_rv")
            recyclerView!!.layoutManager!!.onRestoreInstanceState(savedRecyclerLayoutState)
        }*/

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as ICallbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.cat_list, container, false)

        mainViewModel.catList.observe(viewLifecycleOwner, { cats ->
            Log.e("CatFragment", "cats" + cats[0].id)
        })

        recyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        recyclerView!!.adapter = adapter

        mainViewModel.catList.observe( viewLifecycleOwner, { cats ->
            Log.e("CatFragment", "cats " + cats[0].url)

            adapter = context?.let { CatAdapter(it, cats) }
            adapter!!.notifyDataSetChanged()
            recyclerView!!.adapter = adapter
        })

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //outState.putParcelable("save_rv", recyclerView!!.layoutManager!!.onSaveInstanceState())

    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): CatFragment {
            return CatFragment()
        }
    }
}
