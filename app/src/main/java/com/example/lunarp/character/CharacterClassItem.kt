package com.example.lunarp.character

import com.example.lunarp.classes.ClassesClassItem
import com.example.lunarp.races.RaceClassItem

data class CharacterBean(
    var id: Int,
    val alignement: String,
    val charisma: Int,
    val `class`: ClassesClassItem?,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val inventory: Int,
    val level: Int,
    val name: String,
    val race: RaceClassItem?,
    val spells: List<Int>,
    val strength: Int,
    val user: Int,
    val wisdom: Int
)

data class CharacterClassItem(
    val alignment: String,
    val charisma: Int,
    val `class`: Int,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val inventory: List<Int>,
    val level: Int,
    val name: String,
    val race: Int,
    val spells: List<Int>,
    val strength: Int,
    val user: Int,
    val wisdom: Int
    )
