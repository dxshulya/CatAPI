package com.dxshulya.catapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.*

class CatFragment : Fragment() {

    private lateinit var image: ImageView

    //private var catService: APIInterface? = null
    lateinit var mService: RetrofitServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image = view.findViewById(R.id.image)
        //catService = API.getClient()?.create(APIInterface::class.java)
        mService = Common.retrofitService

        performRequest()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cat_picture, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CatFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun performRequest() {

        mService.getCatList().enqueue(object : Callback<MutableList<Cat>> {
            override fun onFailure(call: Call<MutableList<Cat>>, t: Throwable) {
                Log.e("ошибка", "ошибка", t)
            }

            override fun onResponse(
                call: Call<MutableList<Cat>>,
                response: Response<MutableList<Cat>>
            ) {
                response.isSuccessful
                val body = response.body()
                Picasso.get().load(body?.firstOrNull()?.url).into(image)

                Log.e("массив", "ошибка")
            }
        })
    }
}
