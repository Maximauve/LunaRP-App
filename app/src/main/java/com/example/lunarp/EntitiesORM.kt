package com.example.lunarp

class EntitiesORM{
}


data class Item (
    val id: Int,
    val name: String,
    val price: Int,
    val damages: Int,
    val defense: Int,
    val regeneration: Int,
    val image: String,
    val description: String
)