package com.example.where_to_eat.room.restaurant

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.where_to_eat.Restaurants
import com.example.where_to_eat.room.Profile
import com.example.where_to_eat.room.ProfileDatabase

@Database(entities = [Restaurants::class], version = 1, exportSchema = false)
abstract class RestaurantDatabase:RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao

    companion object{
        @Volatile
        private var INSTANCE: RestaurantDatabase?= null

        fun getDatabase(context: Context): RestaurantDatabase{
            val tempInstance = RestaurantDatabase.INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    "restaurant_database"
                ).build()

                RestaurantDatabase.INSTANCE = instance

                return instance
            }
        }
    }
}