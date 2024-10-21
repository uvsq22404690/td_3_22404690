package dns;

import java.io.IOException;

public class DnsApp {
    // Point d'entrée de l'application
    public static void main(String[] args) {
        try {
            // Crée une instance de Dns avec le chemin du fichier de données
            Dns dns = new Dns("test-dns.txt");
            // Crée une interface utilisateur textuelle pour interagir avec le DNS
            DnsTUI tui = new DnsTUI(dns);
            tui.run(); // Démarre l'exécution de l'interface utilisateur
        } catch (Exception e) {
            // Affiche un message d'erreur en cas d'exception
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}
