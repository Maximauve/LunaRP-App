package com.example.lunarp.character


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CharacterInterface {
    @POST("characters/create")
    fun createCharacter(@Body character: CharacterBean): Call<Any>

    @GET("characters")
    fun getAll():Call<List<Character>>
}
