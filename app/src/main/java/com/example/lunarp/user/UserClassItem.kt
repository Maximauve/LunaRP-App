package com.example.lunarp.user

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
    val characters: Array<com.example.lunarp.character.Character>

)

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val role: String,
);