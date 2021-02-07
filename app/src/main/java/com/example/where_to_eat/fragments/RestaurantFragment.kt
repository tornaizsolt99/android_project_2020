package com.example.where_to_eat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.RestaurantsAdapter
import com.example.where_to_eat.RestaurantsList
import com.example.where_to_eat.api.ApiClient
import com.example.where_to_eat.databinding.FragmentRestaurantBinding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                val response= ApiClient.getInstance().fetchAllRestaurants().execute().body()
                setRestautants(response!!.restaurants)
            }
            catch (e:Exception){

                Log.d("zsolt",e.toString() )

            }
        }

        return view
    }

    fun setRestautants(restaurants: List<Restaurants>){
        activity?.runOnUiThread {
            binding.restaurantRecyclerview.adapter = RestaurantsAdapter(restaurants)
            binding.restaurantRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.restaurantRecyclerview.setHasFixedSize(true)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}