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



https://github.com/user-attachments/assets/5d750ac1-3858-421d-9fc4-ce30568b4e7b


## Auteur

CHARRAJ Mouad
