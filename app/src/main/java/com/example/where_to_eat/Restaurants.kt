package com.example.where_to_eat

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName= "restaurant_table")
data class Restaurants (
        @PrimaryKey(autoGenerate = false)
        @Expose(serialize = false)
        @SerializedName("id")
        val id: Int,

        @SerializedName("image_url")
        val image_url: String,


        @SerializedName("name")
        val name: String,

        @SerializedName("address")
        val address: String,

        @SerializedName("city")
        val city: String,

        @SerializedName("state")
        val state: String,

        @SerializedName("area")
        val area: String,

        @SerializedName("postal_code")
        val postal_code: String,

        @SerializedName("country")
        val country: String,

        @SerializedName("phone")
        val phone: String,

        @SerializedName("lat")
        val lat: Float,

        @SerializedName("lng")
        val lng: Float,

        @SerializedName("price")
        val price: Short
    )

