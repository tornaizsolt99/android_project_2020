package com.example.where_to_eat.fragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.where_to_eat.R
import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.api.ApiClient
import com.example.where_to_eat.databinding.FragmentDetailsBinding
import com.example.where_to_eat.databinding.FragmentRestaurantBinding
import com.example.where_to_eat.room.restaurant.RestaurantDatabase
import com.example.where_to_eat.room.restaurant.RestaurantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var nameView: TextView
    private lateinit var locationView: TextView
    private lateinit var priceView: TextView
    private lateinit var phoneView: TextView
    private lateinit var callButton: Button
    private lateinit var mapButton: Button
    private lateinit var geo: String
    private lateinit var favouriteButton: ImageButton


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding =  FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        nameView=binding.name
        locationView=binding.location
        priceView=binding.price
        phoneView=binding.phone
        callButton=binding.call
        mapButton=binding.maps
        favouriteButton=binding.favorite

        val extras=arguments
        if (extras != null) {
            if(extras.get("id")!=null){
                getRestaurant(extras.get("id") as Int)
            }
        }

        callButton.setOnClickListener{
            val number=phoneView.text.toString().trim()
            val intent =Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(number)))
            startActivity(intent)
        }
        mapButton.setOnClickListener{
            val intent= Intent(Intent.ACTION_VIEW, Uri.parse("geo:$geo"))
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }
        val restaurantDao= RestaurantDatabase.getDatabase(requireContext()).restaurantDao()

        favouriteButton.setOnClickListener {
            if(favouriteButton.tag=="border"){
                favouriteButton.tag="favourite"
                favouriteButton.setBackgroundResource(R.drawable.ic_baseline_favourite_24)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val item= ApiClient.getInstance().getRestaurant(extras?.get("id") as Int).execute().body()

                        if (item != null) {
                            RestaurantRepository(restaurantDao).addRestaurant(item)
                            Log.d("zsolt","hozzaadva")
                        }
                        else{
                            Log.d("zsolt","hibaa")
                        }

                    }catch(e: Exception){
                        Log.d("zsolt",e.toString())
                    }
                }
            }
            else{
                favouriteButton.tag="border"
                favouriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val item= ApiClient.getInstance().getRestaurant(extras?.get("id") as Int).execute().body()

                        if (item != null) {
                            RestaurantRepository(restaurantDao).deleteFavouriteRestaurant(item)
                            Log.d("zsolt","hozzaadva")
                        }
                        else{
                            Log.d("zsolt","hibaa")
                        }

                    }catch(e: Exception){
                        Log.d("zsolt",e.toString())
                    }
                }
            }
        }



        return view
    }

    fun getRestaurant(id: Int) {

        CoroutineScope(Dispatchers.IO).launch {
            try{
            val restaurant= ApiClient.getInstance().getRestaurant(id).execute().body()
            if (restaurant != null) {
                val restaurantDao= RestaurantDatabase.getDatabase(requireContext()).restaurantDao()
                val favouriteRestaurants=RestaurantRepository(restaurantDao).getFavouriteRestaurants()
                var favourite=false
                for (i in favouriteRestaurants){
                    if(i.id == restaurant.id){
                    favourite=true
                    }
                }
                setRestaurant(restaurant,favourite)
            }
        } catch (e: Exception){
            Log.d("zsolt",e.toString())
        }
    }
}
fun setRestaurant(restaurant: Restaurants,favourite:Boolean){
    activity?.runOnUiThread {
        nameView.text=restaurant.name
        locationView.text= "${restaurant.country} , ${restaurant.state}, ${restaurant.city}, ${restaurant.address}"
        priceView.text= "Price: ${restaurant.price}"
        phoneView.text="Tel.: ${restaurant.phone}"
        geo="${restaurant.lat},${restaurant.lng}"
        if(favourite){
            favouriteButton.tag="favourite"
            favouriteButton.setBackgroundResource(R.drawable.ic_baseline_favourite_24)
        }
        else{
            favouriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
            favouriteButton.tag="border"
        }
    }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}