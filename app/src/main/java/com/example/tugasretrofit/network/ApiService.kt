package com.example.tugasretrofit.network

import com.example.tugasretrofit.model.SuperHeroModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("superhero")
    fun getSuperHero(): Call<SuperHeroModel>
}