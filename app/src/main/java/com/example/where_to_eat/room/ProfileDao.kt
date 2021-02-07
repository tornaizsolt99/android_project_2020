package com.example.where_to_eat.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProfile(profile: Profile)

    @Query("SELECT * FROM profile_table WHERE id = :id ")
    fun getProfile(id: Int):Profile
}