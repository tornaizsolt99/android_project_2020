package com.example.where_to_eat

data class Restaurants (

    val id: Int,
    val image_url: String,
    val name: String,
    val address: String,
    val city: String,
    val state: String,
    val area: String,
    val postal_code: String,
    val country: String,
    val phone: String,
    val lat: Float,
    val lng: Float,
    val price: Short
    )

