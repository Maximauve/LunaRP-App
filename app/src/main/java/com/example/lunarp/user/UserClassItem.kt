package com.example.lunarp.user

import com.example.lunarp.LocalFile

data class UserRegisterClassItem(
    val email: String,
    val id: Int? = null,
    val password: String,
    val role: String,
    val username: String
)

data class UserLoginClassItem(
    val email: String,
    val password: String,
)
data class UserClassItem(
    val email: String,
    val id : Int,
    val role: String,
    val username: String,
    val token : String,
    val password: String,
    val characters: Array<com.example.lunarp.character.Character>,
    val image : LocalFile?,
    val userId: Int?

)

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
);