package com.example.where_to_eat.room

public class ProfileRepository (private val profileDao: ProfileDao) {

    suspend fun getProfile(id: Int): Profile= profileDao.getProfile(id)

    suspend fun addProfile(profile: Profile){
        profileDao.addProfile(profile)
    }
}