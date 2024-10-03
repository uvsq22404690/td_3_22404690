package dns;

import java.io.IOException;

public class DnsApp {
    public static void main(String[] args) {
        try {
            Dns dns = new Dns("test-dns.txt"); // Assurez-vous que ce fichier existe.
            DnsTUI tui = new DnsTUI(dns);
            tui.run();
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}
