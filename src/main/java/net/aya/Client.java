package net.aya;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        System.out.println("Tentative de connexion au serveur...");

        try (
                // Connexion au serveur
                Socket socket = new Socket(SERVER_HOST, SERVER_PORT);

                // Flux de communication
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // Scanner pour lire ce que tu tapes sur ton clavier
                Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("Connecté au serveur avec succès !");
            System.out.println("Commandes disponibles : 'hello', 'time', 'bye'\n-----------------------------------");

            String userInput;
            while (true) {
                System.out.print("Moi : ");
                userInput = scanner.nextLine();

                // Envoi du message au serveur
                out.println(userInput);

                // Lecture de la réponse du serveur
                String response = in.readLine();
                System.out.println("Serveur : " + response);

                // Si on a écrit "bye", on quitte la boucle
                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erreur côté client : " + e.getMessage());
        }
        System.out.println("Fin du programme client.");
    }
}