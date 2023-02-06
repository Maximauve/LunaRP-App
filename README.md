# LunaRP-App

Ce projet est une application de Donjon et Dragon. Le but est de pouvoir créer ses personnages facilement et rapidement.

- [LunaRP-App](#lunarp-app)
	- [Composition de l'équipe](#composition-de-léquipe)
	- [Fonctionnalités](#fonctionnalités)
		- [Application mobile](#application-mobile)
		- [Application web](#application-web)
		- [API](#api)
	- [Liste des Vues](#liste-des-vues)
		- [Application mobile](#application-mobile-1)
		- [Application web](#application-web-1)
	- [Trello](#trello)
	- [Objectif pour la prochaine séance](#objectif-pour-la-prochaine-séance)
## Composition de l'équipe

- REYPE Costa : Développeur Mobile
- FERREIRA SILVA Mattéo : Développeur Web Fullstack
- MOURGUES Maxime : Développeur Web Backend & API
- HERAUD Sandra : Développeuse Frontend UX/UI

## Fonctionnalités

<!-- TODO : Essayer de trouver une fonctionnalité avancée en terme d'algo -->
### Application mobile

Techno utilisée : **Kotlin**

Cette application présentera les fonctionnalités suivantes :
- Système d'authentification
- Création d'un personnage Dnd : [Donjon et Dragon](https://www.aidedd.org/dnd-5/)
	- Plusieurs pages de création (race, classe, sorts)
- Interface personnage simplifiée
  - Les 6 caractéristiques principales du personnage (Force, Dextérité, Constitution, Intelligence, Sagesse, Charisme)
  - Pv
  - Niveau
  - Photo
- Un livre de sorts
- Un lancé de dés
- Système de report de profil
- Un utilisateur peut lancer un campagne qu'il a créé au préalable, et que les joueurs  confirme leur participation à cette campagne

Lorsqu'une campagne est lancée :
- Un chronomètre est lancé, pour calculer le temps de campagne (temps de jeu)
- MJ :
	- Contrôle les tours des joueurs
		- Active le tour d'un joueur afin qu'il puisse faire certaines actions
	- Le sort qu'un joueur à utilisé peut être réccupéré après un temps de repos donné par le MJ
  	- Simple boutton qui permet de faire reposer le joueur (Repos long ou court)
  	- Repos court : Récupère uniquement des PV
  	- Repos long : Récupère des PV et ses sorts.
	- Le MJ peut modifier les caractéristiques du personnage d'un joueur (PV, etc...)
	- La fiche des PNJ et créatures présente dans la campagne est accessible par le MJ
	- Pouvoir lancer un dé

- Joueurs :	
	- Accès au statistiques de son propre personnage (PV, etc...)	
	- Les joureurs peuvent lancer un sort (si son tour est débloqué)
		- Si un sort est lancé, une gestion de sorts est mise en place afin de ne pas pouvoir utiliser un sort qu'on a déjà lancé. (le sort est grisé de sa liste, et il ne peut donc plus l'utiliser)
	- Pouvoir lancer un dé

### [Application web](https://github.com/Maximauve/LunaRP-Web)

Techno utilisée : **Symfony**

Elle disposera des fonctionnalités suivantes :
- Un pannel administrateur :
  - Gestion des utilisateurs
  - Gestion des campagnes
  - Modération (en rapport avec les report de profil)
- Création campagnes

Création de campagne : 
- Un utilisateur connecté peut créer un campagne
- Il assigne des spécificités à sa campagne (quelles races sont présentes, quels sorts peuvent être utilisés, quelles classes sont disponibles etc...)
- Il peut ensuite inviter des joueurs à rejoindre sa campagne (grâce à un code donné par lors de la création de cette campagne)


### [API](https://github.com/Maximauve/LunaRP-API)

Techno utilisée : **NestJs**

Elle permettra d'interagir avec l'application mobile et web, et fera office de passerelle entre les applications, et la base de donnée.


## Liste des Vues

### Application mobile
- Page de connexion
- Page d'inscription
- Page avec la liste des personnages que l'on a créé, et possibilité d'en créer un. Si aucun personnage n'a été créé, un bouton mis en évidence nous suggère d'en créer un.
- Page d'inscription à une campagne ( scanner QR code ou input code)
- lancé de dés, avec selection de dé.
- Page de fiche de personnage avec en-tête fixe (nom, classe, race, pv, vitesse, armure, pièces) Navigation swipe avec :
  - Caractéristiques précises (force, sagesse, ...)
  - Sorts
  - Notes personnelles
- Création d'un personnage en plusieurs étapes :
  - Race
  - Classe
  - Sorts
  - Notes personnelles
### Application web
- Page de connexion
- Page d'inscription
- 
**Si Admin :**
- Pannel administateur : Dashboard avec liste des campagnes, user et report de profil
- Page des utilisateurs :
	- Modifier le profil d'un utilisateur
	- Mettre des sanctions (avertissement, bannissement, etc...)
- Page des campagnes :
	- Voir les caractéristiques d'une campagne (le créateur de la campagne, les joueurs enregistrés...)

**Si User :**
- Page d'accueil, avec les campagnes auxquelles l'utilisateur est inscrit, les personnages qu'il a créé, et inscrit dans une campagne
- Page de création de campagne
- 
## Trello

[Vous trouverez ici le lien du trello](https://trello.com/b/NnMnuTdq/conduite-de-projet)



## Objectif pour la prochaine séance
- Rassembler toutes les tâches dans le trello
- Faire les schémas de la BDD et la construire
- Commencer à remplir la BDD
- Initialiser tous les projets (NestJS, Symfony, Kotlin)