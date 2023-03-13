package com.example.lunarp.character

data class CharacterClassItem(
    val alignement: String,
    val charisma: Int,
    val `class`: Int,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val level: Int,
    val name: String,
    val race: Int,
    val spells: List<Int>,
    val strength: Int,
    val user: Int,
    val wisdom: Int
)

data class CharacterBean(
    var id: Int,
    val alignement: String,
    val charisma: Int,
    val `class`: Int,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val level: Int,
    val name: String,
    val race: Int,
    val spells: List<Int>,
    val strength: Int,
    val user: Int,
    val wisdom: Int
    )
