package com.example.lunarp.character

import com.example.lunarp.classes.ClassesClassItem
import com.example.lunarp.item.Inventory
import com.example.lunarp.races.Race
import com.example.lunarp.races.RaceClassItem
import com.example.lunarp.user.User
import com.example.lunarp.user.UserClassItem

data class CharacterDelete(
    val id : Int
)

data class CharacterBean(
    val alignment: String,
    val campaign: List<Any>?,
    val charisma: Int,
    val classe: Int,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val inventory: List<Int>?,
    val level: Int,
    val name: String,
    val race: Int,
    val spells: List<Int>,
    val strength: Int,
    val wisdom: Int
)

data class CharacterUpdate(
    val id: Int,
    val alignment: String,
    val campaign: List<Any>?,
    val charisma: Int,
    val classe: Int,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val intelligence: Int,
    val inventory: List<Int>?,
    val level: Int,
    val name: String,
    val race: Int,
    val spells: List<Int>,
    val strength: Int,
    val wisdom: Int
)

data class CharacterClassItem(
    val alignment: String,
    val charisma: Int,
    val classe: Int,
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

data class Character(
    val alignment: String,
    val campaign: List<Any>?,
    val charisma: Int,
    val classe: ClassesClassItem,
    val constitution: Int,
    val description: String,
    val dexterity: Int,
    val experience: Int,
    val id: Int,
    val intelligence: Int,
    val inventory: List<Inventory>,
    val level: Int,
    val name: String,
    val race: RaceClassItem,
    val spells: List<Any>,
    val strength: Int,
    val user: User,
    val wisdom: Int
)

/*
[
  {
    "id": 3,
    "name": "Test",
    "level": 1,
    "experience": 0,
    "alignment": "Loyal Bon",
    "strength": 10,
    "dexterity": 10,
    "constitution": 10,
    "intelligence": 10,
    "wisdom": 10,
    "charisma": 10,
    "description": "Ceci est un test",
    "user": {
      "id": 1,
      "username": "Toto",
      "email": "toto@gmail.com",
      "password": "0124d514ed76893c3d745cf6ace3fe4079c22b120ecfc41805616db546629b08fdc312a1726d05276489b1ab0a9933f3fbb7ba233cad1d1a6a85aa98f56dcfde",
      "role": "Admin"
    },
    "campaign": [],
    "race": {
      "id": 2,
      "name": "Humain",
      "speed": 12,
      "size": "M",
      "description": "Cette créature est la race de base du jeu. On en trouve partout et surtout là où il ne devrait pas être."
    },
    "spells": [],
    "classe": {
      "id": 1,
      "name": "Barde",
      "dice": 8,
      "description": " Le barde est le maître du chant, du discours et de la magie qu’ils contiennent. Les bardes affirment que le multivers est né d’une parole, que les mots des dieux l’ont façonné et que les échos de ces primordiaux Mots de création résonnent toujours à travers le cosmos. La musique des bardes tente de capturer et d’exploiter ces échos, en les entrelaçant subtilement dans leurs sorts et leurs pouvoirs. La plus grande force du barde est son incontestable polyvalence. De nombreux bardes préfèrent demeurer en retrait lors d’un combat, utilisant plutôt la magie pour inspirer leurs alliés et gêner leurs ennemis. Mais les bardes sont aussi capables de bien se défendre au corps à corps si cela est nécessaire, employant leur magie pour renforcer armes et armure. Leurs sorts tendent vers le charme et les illusions plutôt que la destruction brutale. Ils possèdent un large éventail de connaissance sur de nombreux sujets et une aptitude naturelle qui leur permet de réussir tout ce qu’ils entreprennent. Les bardes deviennent maîtres des talents qu’ils décident de perfectionner, que cela consiste à jouer d'un instrument ou à posséder des connaissances ésotériques."
    },
    "inventory": [
      {
        "id": 3,
        "quantity": 1
      },
      {
        "id": 4,
        "quantity": 1
      }
    ]
  },
  {
    "id": 6,
    "name": "Test3",
    "level": 1,
    "experience": 0,
    "alignment": "Loyal Bon",
    "strength": 10,
    "dexterity": 10,
    "constitution": 10,
    "intelligence": 10,
    "wisdom": 10,
    "charisma": 10,
    "description": "Ceci est un test",
    "user": {
      "id": 1,
      "username": "Toto",
      "email": "toto@gmail.com",
      "password": "0124d514ed76893c3d745cf6ace3fe4079c22b120ecfc41805616db546629b08fdc312a1726d05276489b1ab0a9933f3fbb7ba233cad1d1a6a85aa98f56dcfde",
      "role": "Admin"
    },
    "campaign": [],
    "race": {
      "id": 2,
      "name": "Humain",
      "speed": 12,
      "size": "M",
      "description": "Cette créature est la race de base du jeu. On en trouve partout et surtout là où il ne devrait pas être."
    },
    "spells": [],
    "classe": {
      "id": 1,
      "name": "Barde",
      "dice": 8,
      "description": " Le barde est le maître du chant, du discours et de la magie qu’ils contiennent. Les bardes affirment que le multivers est né d’une parole, que les mots des dieux l’ont façonné et que les échos de ces primordiaux Mots de création résonnent toujours à travers le cosmos. La musique des bardes tente de capturer et d’exploiter ces échos, en les entrelaçant subtilement dans leurs sorts et leurs pouvoirs. La plus grande force du barde est son incontestable polyvalence. De nombreux bardes préfèrent demeurer en retrait lors d’un combat, utilisant plutôt la magie pour inspirer leurs alliés et gêner leurs ennemis. Mais les bardes sont aussi capables de bien se défendre au corps à corps si cela est nécessaire, employant leur magie pour renforcer armes et armure. Leurs sorts tendent vers le charme et les illusions plutôt que la destruction brutale. Ils possèdent un large éventail de connaissance sur de nombreux sujets et une aptitude naturelle qui leur permet de réussir tout ce qu’ils entreprennent. Les bardes deviennent maîtres des talents qu’ils décident de perfectionner, que cela consiste à jouer d'un instrument ou à posséder des connaissances ésotériques."
    },
    "inventory": [
      {
        "id": 5,
        "quantity": 1
      },
      {
        "id": 6,
        "quantity": 1
      }
    ]
  }
]
 */
