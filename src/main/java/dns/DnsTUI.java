package dns;

import java.util.Scanner;

public class DnsTUI {
    private final Dns dns;

    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Bienvenue dans le système DNS. Tapez 'quit' pour quitter.");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("quit")) {
                break;
            }

            try {
                executeCommand(command);
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
    }

    private void executeCommand(String command) {
        // Cette méthode sera développée pour analyser et exécuter les commandes.
        System.out.println("Commande reçue : " + command);
    }
}
