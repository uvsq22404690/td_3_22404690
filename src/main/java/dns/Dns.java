package dns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets; // Pour utiliser l'encodage UTF-8
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Classe représentant le serveur DNS
public class Dns {
    private Map<String, DnsItem> database = new HashMap<>(); // Base de données des noms de machines et adresses IP
    private Path filePath; // Chemin du fichier de données

    // Constructeur qui initialise le chemin du fichier et charge la base de données
    public Dns(String filePath) {
        this.filePath = Paths.get(filePath); // Convertit le chemin de fichier en objet Path
        loadDatabase(); // Charge les données du fichier dans la base de données
    }

    // Méthode pour charger la base de données à partir du fichier
    private void loadDatabase() {
        try {
            // Lit toutes les lignes du fichier
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] parts = line.split(" "); // Divise chaque ligne en parties
                if (parts.length == 2) { // Vérifie que chaque ligne contient une machine et une adresse IP
                    NomMachine nomMachine = new NomMachine(parts[0]); // Crée une instance de NomMachine
                    AdresseIP adresseIP = new AdresseIP(parts[1]); // Crée une instance d'AdresseIP
                    DnsItem dnsItem = new DnsItem(nomMachine, adresseIP); // Crée un DnsItem
                    database.put(nomMachine.getNom(), dnsItem); // Ajoute l'item à la base de données
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Affiche l'erreur si le fichier ne peut pas être lu
        }
    }

    // Méthode pour obtenir un DnsItem à partir du nom de machine
    public DnsItem getItemByNom(String nomMachine) {
        return database.get(nomMachine); // Retourne l'item correspondant dans la base de données
    }

    // Méthode pour obtenir un DnsItem à partir d'une adresse IP
    public DnsItem getItemByAdresse(String adresseIP) {
        // Recherche dans les valeurs de la base de données
        for (DnsItem item : database.values()) {
            if (item.getAdresseIP().toString().equals(adresseIP)) { // Vérifie si l'adresse IP correspond
                return item; // Retourne l'item correspondant
            }
        }
        return null; // Retourne null si aucune correspondance n'est trouvée
    }

    // Méthode pour obtenir tous les items d'un domaine donné
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> items = new ArrayList<>();
        // Recherche les items dont le nom se termine par le domaine
        for (DnsItem item : database.values()) {
            if (item.getNomMachine().getNom().endsWith("." + domaine)) {
                items.add(item); // Ajoute l'item à la liste si le domaine correspond
            }
        }
        return items; // Retourne la liste des items trouvés
    }

    // Méthode pour ajouter un nouvel item à la base de données
    public void addItem(NomMachine nomMachine, AdresseIP adresseIP) {
        // Vérifie si le nom de machine existe déjà
        if (database.containsKey(nomMachine.getNom())) {
            throw new IllegalArgumentException("Le nom de machine existe déjà !"); // Lève une exception si c'est le cas
        }
        DnsItem newItem = new DnsItem(nomMachine, adresseIP); // Crée un nouvel DnsItem
        database.put(nomMachine.getNom(), newItem); // Ajoute l'item à la base de données
        saveDatabase(); // Sauvegarde les données dans le fichier
    }

    // Méthode pour sauvegarder la base de données dans le fichier
    private void saveDatabase() {
        try {
            List<String> lines = new ArrayList<>();
            // Prépare les lignes à écrire dans le fichier
            for (DnsItem item : database.values()) {
                lines.add(item.getNomMachine() + " " + item.getAdresseIP()); // Formate chaque ligne
            }
            // Écrit les lignes dans le fichier
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Affiche l'erreur si l'écriture échoue
        }
    }
}
