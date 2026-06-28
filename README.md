#  TP Réseau - Application Client/Serveur Multi-threaded

Ce projet consiste en une architecture réseau complète développée en **Java**. Il implémente un serveur TCP capable de gérer plusieurs connexions simultanées en arrière-plan grâce à la gestion des Threads, ainsi qu'un client textuel interactif.

## Architecture du Projet

Le projet est composé de trois classes principales situées dans le package `net.aya` :
* **`MultiThreadServer.java`** : Le serveur principal qui écoute sur le port 5000 et distribue chaque client entrant à un processus indépendant (Thread).
* **`ClientHandler.java`** : Le gestionnaire de communication dédié à chaque client (Multi-threading via un `ExecutorService`).
* **`Client.java`** : L'application cliente autonome permettant d'envoyer des commandes (`hello`, `time`, `bye`) et de recevoir les réponses du serveur.

---

##  Captures de Réalisation

### 1. Structure du Projet
Voici l'organisation des fichiers sources dans l'environnement de développement IntelliJ :

![Structure du projet](structure_projet.png)

### 2. Démonstration de l'Exécution (Client / Serveur)
Voici le fonctionnement en direct de l'application où le client dialogue avec le serveur via la console :

![Démonstration Exécution](execution_tp.png)
