package com.example.where_to_eat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.where_to_eat.databinding.FragmentRestaurantBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    private var _binding: FragmentRestaurantBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentRestaurantBinding.inflate(inflater, container, false)
        val view = binding.root
       // val apiResponse=ApiClient.getInstance().fetchAllRestaurants().execute().body()
        val clientt=OkHttpClient()

        val request= Request.Builder().url("https://ratpark-api.imok.space/restaurants?country=US&page=1").build()
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response =clientt.newCall(request).execute()
                val gson=Gson()
                 val apiResponse:RestaurantsList =gson.fromJson(response.body()?.charStream(),RestaurantsList::class.java)
                 Log.d("zsolt", "oksii ")
                Log.d("zsolt","naa ${apiResponse.per_page} ${apiResponse.total_entries} ${apiResponse.restaurants[1].name}")
               // binding.textView.text= apiResponse.restaurant[0].name
                activity?.runOnUiThread {
                    binding.restaurantRecyclerview.adapter = RestaurantsAdapter(apiResponse.restaurants)
                    binding.restaurantRecyclerview.layoutManager = LinearLayoutManager(context)
                    binding.restaurantRecyclerview.setHasFixedSize(true)
                }
            }
            catch (e:Exception){

                Log.d("zsolt",e.toString() )

            }
        }



           // Log.d("zsolt","okee ${restaurantsResponse!!.restaurant[0].name }")


        var restaurants= mutableListOf<Restaurants>()

        for(i in 0..20){
            //restaurants.add(restaurantsResponse!!.restaurant[i])
        }









        return view

       // return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RestaurantFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RestaurantFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}