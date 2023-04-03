package com.example.lunarp.races

import com.example.lunarp.languages.LanguageClassItem


data class RaceClassItem(
    val id: Int,
    val description: String,
    val languages: LanguageClassItem,
    val name: String,
    val size: String,
    val speed: Int
)

data class Race(
    val description: String,
    val id: Int,
    val name: String,
    val size: String,
    val speed: Int
)