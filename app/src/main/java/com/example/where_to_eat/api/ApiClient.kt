package com.example.where_to_eat.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val apiService: ApiService

    init{
        val client= OkHttpClient()
        val retrofit= Retrofit.Builder().baseUrl("https://ratpark-api.imok.space/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
        apiService =retrofit.create(ApiService::class.java)
    }
    fun getInstance(): ApiService {
        return apiService
    }


}