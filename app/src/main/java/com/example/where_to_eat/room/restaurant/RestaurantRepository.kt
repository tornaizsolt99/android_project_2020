package com.example.where_to_eat.room.restaurant

import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.room.Profile

class RestaurantRepository(private val restaurantDao: RestaurantDao) {

    suspend fun addRestaurant(restaurant: Restaurants){
        restaurantDao.addRestaurant(restaurant)
    }
    suspend fun getFavouriteRestaurants(): List<Restaurants> = restaurantDao.getFavouriteRestaurants()
    suspend fun deleteFavouriteRestaurant(restaurant: Restaurants){
        restaurantDao.deleteFavouriteRestaurant(restaurant)
    }
}