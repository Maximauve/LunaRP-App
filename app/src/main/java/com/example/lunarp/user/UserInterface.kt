package com.example.lunarp.user

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserInterface {
    @GET("users")
    fun getData(): Call<List<UserClassItem>>

    @POST("users/auth/sign-up")
    fun register(@Body user : UserRegisterClassItem): Call<UserClassItem>

    @POST("/users/auth/login")
    fun login(@Body user: UserLoginClassItem): Call<UserClassItem>
}