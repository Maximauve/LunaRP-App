package com.example.lunarp.classes

import retrofit2.Call
import retrofit2.http.GET

interface ClassInterface {
    @GET("class")
    fun getData(): Call<List<ClassesClassItem>>
}