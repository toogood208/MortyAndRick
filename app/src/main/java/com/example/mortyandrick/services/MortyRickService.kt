package com.example.mortyandrick.services


import com.example.mortyandrick.models.MortyRick
import retrofit2.Call
import retrofit2.http.GET

interface MortyRickService {

    @GET("character")
    fun getMortyAndRickData(): Call<MortyRick>
}