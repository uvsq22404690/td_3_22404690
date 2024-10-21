package dns;

import java.util.List;
import java.util.Scanner;

public class DnsTUI {
    private final Dns dns; // Instance de la classe Dns

    // Constructeur qui prend un objet Dns
    public DnsTUI(Dns dns) {
        this.dns = dns;
    }

    // Méthode pour exécuter l'interface utilisateur
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Bienvenue dans le système DNS. Tapez 'quit' pour quitter.");

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("quit")) {
                break; // Sort de la boucle si la commande est 'quit'
            }

            try {
                executeCommand(command); // Exécute la commande entrée
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage()); // Affiche l'erreur si une exception se produit
            }
        }

        scanner.close(); // Ferme le scanner à la fin
    }

    // Gère la commande 'ls' pour lister les items d'un domaine
    private void handleListCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: ls <domaine>");
            return; // Affiche l'utilisation correcte si les arguments sont manquants
        }

        String domaine = parts[1];
        boolean afficheTout = parts.length > 2 && parts[2].equalsIgnoreCase("-a");
        List<DnsItem> items = dns.getItems(domaine); // Récupère les items du domaine

        if (items.isEmpty()) {
            System.out.println("Aucun item trouvé pour le domaine : " + domaine);
        } else {
            for (DnsItem item : items) {
                // Affiche l'IP ou le nom selon la commande
                if (afficheTout) {
                    System.out.println(item.getAdresseIP() + " " + item.getNomMachine().getNom());
                } else {
                    System.out.println(item.getNomMachine().getNom());
                }
            }
        }
    }

    // Gère la commande 'add' pour ajouter un nouvel item
    private void handleAddCommand(String[] parts) {
        if (parts.length != 3) {
            System.out.println("Usage: add <adresse IP> <nom de machine>");
            return; // Affiche l'utilisation correcte si les arguments sont incorrects
        }

        try {
            AdresseIP adresseIP = new AdresseIP(parts[1]);
            NomMachine nomMachine = new NomMachine(parts[2]);
            dns.addItem(nomMachine, adresseIP); // Ajoute l'item à la base de données
            System.out.println("Item ajouté : " + nomMachine.getNom() + " -> " + adresseIP);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage()); // Affiche l'erreur si l'ajout échoue
        }
    }

    // Gère la commande 'nom' pour récupérer l'adresse IP d'un nom de machine
    private void handleNomCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: nom <nom de machine>");
            return; // Affiche l'utilisation correcte si les arguments sont incorrects
        }

        DnsItem item = dns.getItemByNom(parts[1]);
        if (item != null) {
            System.out.println("Adresse IP : " + item.getAdresseIP());
        } else {
            System.out.println("Nom de machine introuvable : " + parts[1]);
        }
    }

    // Gère la commande 'adr' pour récupérer le nom de machine à partir d'une adresse IP
    private void handleAdrCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: adr <adresse IP>");
            return; // Affiche l'utilisation correcte si les arguments sont incorrects
        }

        DnsItem item = dns.getItemByAdresse(parts[1]);
        if (item != null) {
            System.out.println("Nom de machine : " + item.getNomMachine().getNom());
        } else {
            System.out.println("Adresse IP introuvable : " + parts[1]);
        }
    }

    // Exécute la commande entrée par l'utilisateur
    private void executeCommand(String command) {
        String[] parts = command.split(" ");
        String mainCommand = parts[0];
        switch (mainCommand.toLowerCase()) {
            case "ls":
                handleListCommand(parts);
                break;
            case "add":
                handleAddCommand(parts);
                break;
            case "nom":
                handleNomCommand(parts);
                break;
            case "adr":
                handleAdrCommand(parts);
                break;
            default:
                System.out.println("Commande inconnue : " + command);
        }
    }
}
