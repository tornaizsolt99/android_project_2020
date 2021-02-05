package com.example.where_to_eat.api

import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.RestaurantsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("restaurants")
    fun fetchAllRestaurants(): Call<RestaurantsList>

    @GET("restaurants/{id}")
    fun getRestaurant(@Path("id")id: Int): Call<Restaurants>
}