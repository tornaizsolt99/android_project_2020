package com.example.where_to_eat

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("restaurants")
    fun fetchAllRestaurants(): Call<RestaurantsList>
}