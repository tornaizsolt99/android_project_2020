package com.example.where_to_eat

import com.google.gson.annotations.SerializedName

data class RestaurantsList (
        @SerializedName("total_entries")
        val total_entries: Int,

        @SerializedName("per_page")
        val per_page: Short,

        @SerializedName("restaurants")
        val restaurants: List<Restaurants>
)
