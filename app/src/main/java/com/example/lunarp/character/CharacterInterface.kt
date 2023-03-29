package com.example.lunarp.character

import com.example.lunarp.classes.ClassesClassItem
import com.example.lunarp.user.UserClassItem
import com.example.lunarp.user.UserRegisterClassItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CharacterInterface {
    @POST("characters/create")
    fun createCharacter(@Body user : CharacterClassItem): Call<CharacterClassItem>
}
