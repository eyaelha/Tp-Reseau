
package net.aya;
    import java.io.BufferedReader;
    import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
    public class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Le constructeur reçoit le socket du client connecté
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            String clientIp = clientSocket.getInetAddress().getHostAddress();

            System.out.println("Client connecté : " + clientIp);
            System.out.println("Thread " + threadName + " traite le client");

            try (
                    // Initialisation des flux de lecture (in) et d'écriture (out)
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
            ) {
                String inputLine;
                // Boucle de lecture des messages du client
                while ((inputLine = in.readLine()) != null) {
                    // Nettoyage de la chaîne (enlève les espaces invisibles envoyés par Telnet)
                    inputLine = inputLine.trim();

                    System.out.println("Message reçu : " + inputLine);

                    // Logique de réponse selon les consignes du TP
                    if (inputLine.equalsIgnoreCase("hello")) {
                        out.println("Bonjour client !");
                    } else if (inputLine.equalsIgnoreCase("time")) {
                        out.println(new Date().toString());
                    } else if (inputLine.equalsIgnoreCase("bye")) {
                        out.println("Connexion fermée");
                        break; // On sort de la boucle pour fermer ce client
                    } else {
                        out.println("Message reçu : [" + inputLine + "]");
                    }
                }
            } catch (Exception e) {
                System.err.println("Erreur avec le client " + clientIp + " : " + e.getMessage());
            } finally {
                // Fermeture propre du socket pour ce client
                try {
                    clientSocket.close();
                } catch (Exception e) {
                    System.err.println("Erreur fermeture socket : " + e.getMessage());
                }
            }

    }
}
