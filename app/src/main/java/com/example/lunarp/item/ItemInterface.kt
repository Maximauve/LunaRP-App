package com.example.lunarp.item

import retrofit2.Call
import retrofit2.http.GET

interface ItemInterface {
    @GET("items")
    fun getData(): Call<List<ItemClassItem>>
}