# NumberBook API

Projet simple de gestion et synchronisation de contacts.

Le projet contient :

- un backend PHP/MySQL pour stocker, lister et rechercher les contacts ;
- une application Android qui lit les contacts du telephone, les affiche et les synchronise avec le serveur.

## Structure du projet

```text
numberbook-api/
├── backend/
│   ├── api/
│   │   ├── getAllContacts.php
│   │   ├── insertContact.php
│   │   └── searchContact.php
│   ├── config/
│   │   └── Database.php
│   ├── model/
│   │   └── Contact.php
│   ├── service/
│   │   └── ContactService.php
│   └── numberbook.sql
└── frontend/
    └── app Android
```

## Fonctionnalites

- Charger les contacts du telephone.
- Afficher les contacts dans une liste.
- Synchroniser les contacts vers le backend.
- Recuperer tous les contacts depuis le serveur.
- Rechercher un contact par nom ou numero.

## Technologies utilisees

- Backend : PHP, MySQL
- Frontend : Android Java
- API HTTP : Retrofit + Gson
- Interface Android : RecyclerView

## Installation du backend

1. Copier le dossier `backend` dans le dossier web local, par exemple `htdocs/numberbook-api` avec XAMPP.
2. Demarrer Apache et MySQL.
3. Importer le fichier SQL :

```sql
backend/numberbook.sql
```

4. Verifier la configuration de la base de donnees dans :

```text
backend/config/Database.php
```

## Routes API

Base URL utilisee par l'application Android :

```text
http://10.0.2.2/numberbook-api/api/
```

Routes disponibles :

| Methode | Route | Description |
| --- | --- | --- |
| GET | `getAllContacts.php` | Retourne tous les contacts |
| POST | `insertContact.php` | Ajoute un contact |
| GET | `searchContact.php?keyword=...` | Recherche un contact |

Exemple de donnees pour l'ajout d'un contact :

```json
{
  "name": "Ali",
  "phone": "0600000000"
}
```

## Lancement de l'application Android

1. Ouvrir le dossier `frontend` avec Android Studio.
2. Lancer le backend local avec XAMPP ou un serveur equivalent.
3. Verifier que l'URL dans `RetrofitClient_mouad.java` correspond au serveur utilise.
4. Lancer l'application sur un emulateur Android.
5. Autoriser la permission de lecture des contacts.

## Video demo

Ajouter ici la video de demonstration du projet :

```text
Lien video : ................................................
```

## Auteur

Projet realise dans le cadre du lab20.
