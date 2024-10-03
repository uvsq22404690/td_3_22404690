package dns;

import java.util.List;
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
    private void handleListCommand(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Usage: ls <domaine>");
            return;
        }

        String domaine = parts[1];
        boolean afficheTout = parts.length > 2 && parts[2].equalsIgnoreCase("-a");
        List<DnsItem> items = dns.getItems(domaine);

        if (items.isEmpty()) {
            System.out.println("Aucun item trouvé pour le domaine : " + domaine);
        } else {
            for (DnsItem item : items) {
                if (afficheTout) {
                    System.out.println(item.getAdresseIP() + " " + item.getNomMachine().getNom());
                } else {
                    System.out.println(item.getNomMachine().getNom());
                }
            }
        }
    }

    private void handleAddCommand(String[] parts) {
        if (parts.length != 3) {
            System.out.println("Usage: add <adresse IP> <nom de machine>");
            return;
        }

        try {
            AdresseIP adresseIP = new AdresseIP(parts[1]);
            NomMachine nomMachine = new NomMachine(parts[2]);
            dns.addItem(nomMachine, adresseIP);
            System.out.println("Item ajouté : " + nomMachine.getNom() + " -> " + adresseIP);
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void handleNomCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: nom <nom de machine>");
            return;
        }

        DnsItem item = dns.getItemByNom(parts[1]);
        if (item != null) {
            System.out.println("Adresse IP : " + item.getAdresseIP());
        } else {
            System.out.println("Nom de machine introuvable : " + parts[1]);
        }
    }

    private void handleAdrCommand(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: adr <adresse IP>");
            return;
        }

        DnsItem item = dns.getItemByAdresse(parts[1]);
        if (item != null) {
            System.out.println("Nom de machine : " + item.getNomMachine().getNom());
        } else {
            System.out.println("Adresse IP introuvable : " + parts[1]);
        }
    }

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
