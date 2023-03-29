package com.example.lunarp.races

import retrofit2.Call
import retrofit2.http.GET

interface RaceInterface {
    @GET("races")
    fun getData(): Call<List<RaceClassItem>>
}