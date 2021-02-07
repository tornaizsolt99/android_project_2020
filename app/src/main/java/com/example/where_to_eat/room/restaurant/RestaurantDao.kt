package com.example.where_to_eat.room.restaurant

import androidx.room.*
import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.room.Profile


@Dao
interface RestaurantDao {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addRestaurant(restaurants: Restaurants)

        @Query("SELECT * FROM restaurant_table ")
        fun getFavouriteRestaurants(): List<Restaurants>

       @Delete
       suspend fun deleteFavouriteRestaurant(restaurant: Restaurants)

}