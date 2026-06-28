package net.aya;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer {
    private static final int PORT = 5000;
    private static final int THREAD_POOL_SIZE = 5;

    public static void main(String[] args) {
        // Création du pool fixe de 5 threads
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        // Ouverture du ServerSocket sur le port 5000
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Le serveur TCP est démarré sur le port " + PORT + "...");
            System.out.println("En attente de connexions clients...\n-----------------------------------");

            while (true) {
                // Attente d'un client
                Socket clientSocket = serverSocket.accept();

                // Soumission de la gestion du client au pool de threads
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Erreur sur le serveur : " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}