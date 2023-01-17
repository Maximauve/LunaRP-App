# LunaRP-App

Ce projet est une application de Donjon et Dragon. Le but est de pouvoir créer ses personnages facilement et rapidement.

- [LunaRP-App](#lunarp-app)
	- [Composition de l'équipe](#composition-de-léquipe)
	- [Fonctionnalités](#fonctionnalités)
		- [Application mobile](#application-mobile)
		- [Application web](#application-web)
		- [API](#api)
	- [Trello](#trello)
## Composition de l'équipe

- REYPE Costa : Développeur Mobile
- FERREIRA SILVA Mattéo : Développeur Web Fullstack
- MOURGUES Maxime : Développeur Web Backend & API
- HERAUD Sandra : Développeuse Frontend UX/UI

## Fonctionnalités

### Application mobile

Techno utilisée : **Kotlin**

Cette application présentera les fonctionnalités suivantes :
- Système d'authentification
- Création d'un personnage Dnd : [Donjon et Dragon](https://www.aidedd.org/dnd-5/)
	- Plusieurs pages de création (race, classe, sorts)
- Interface personnage simplifiée
- Un livre de sorts
- Un lancé de dés
- Système de report de profil
- Un utilisateur peut lancer un campagne qu'il a créé au préalable, et que les joueurs  confirme leur participation à cette campagne

Lorsqu'une campagne est lancée :
- MJ :
	- Contrôle les tours des joueurs
	- Le sort qu'un joueur à utilisé peut être réccupéré après un temps de repos donné par le MJ
	- Le MJ peut modifier les caractéristiques du personnage d'un joueur (PV, etc...)
	- La fiche des PNJ et créatures présente dans la campagne est accessible par le MJ
	- Pouvoir lancer un dé

- Joueurs :	
	- Accès au statistiques de son propre personnage (PV, etc...)	
	- Les joureurs peuvent lancer un sort (le MJ doit le débloquer)
		- Si un sort est lancé, une gestion de sorts est mise en place afin de ne pas pouvoir utiliser un sort qu'on a déjà lancé.
	- Pouvoir lancer un dé

### [Application web](https://github.com/Maximauve/LunaRP-Web)

Techno utilisée : **Symfony**

Elle disposera des fonctionnalités suivantes :
- Un pannel administrateur :
- - Gestion des utilisateurs
- - Modération (en rapport avec les report de profil)
- Création campagnes

Création de campagne : 
- Un utilisateur connecté peut créer un campagne
- Il assigne des spécificités à sa campagne (quelles races sont présentes, quels sorts peuvent être utilisés, quelles classes sont disponibles etc...)
- Il peut ensuite inviter des joueurs à rejoindre sa campagne (grâce à un code donné par lors de la création de cette campagne)


### [API](https://github.com/Maximauve/LunaRP-API)

Techno utilisée : **NestJs**

Elle permettra d'interagir avec l'application mobile et web, et fera office de passerelle entre les applications, et la base de donnée.


## Trello

[Vous trouverez ici le lien du trello](https://trello.com/b/NnMnuTdq/conduite-de-projet)