package com.example.where_to_eat.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile_table")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("address")
    val adress:String,
    @SerializedName("phone")
    val phone:String,
    @SerializedName("email")
    val email:String

)