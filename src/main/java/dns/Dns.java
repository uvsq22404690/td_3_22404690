package dns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets; // Ajoute ceci
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Reste du code...


public class Dns {
    private Map<String, DnsItem> database = new HashMap<>();
    private Path filePath;

    public Dns(String filePath) {
        this.filePath = Paths.get(filePath);
        loadDatabase();
    }

    private void loadDatabase() {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    NomMachine nomMachine = new NomMachine(parts[0]);
                    AdresseIP adresseIP = new AdresseIP(parts[1]);
                    DnsItem dnsItem = new DnsItem(nomMachine, adresseIP);
                    database.put(nomMachine.getNom(), dnsItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DnsItem getItemByNom(String nomMachine) {
        return database.get(nomMachine);
    }

    public DnsItem getItemByAdresse(String adresseIP) {
        for (DnsItem item : database.values()) {
            if (item.getAdresseIP().toString().equals(adresseIP)) {
                return item;
            }
        }
        return null;
    }

    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> items = new ArrayList<>();
        for (DnsItem item : database.values()) {
            if (item.getNomMachine().getNom().endsWith("." + domaine)) {
                items.add(item);
            }
        }
        return items;
    }

    public void addItem(NomMachine nomMachine, AdresseIP adresseIP) {
        if (database.containsKey(nomMachine.getNom())) {
            throw new IllegalArgumentException("Le nom de machine existe déjà !");
        }
        DnsItem newItem = new DnsItem(nomMachine, adresseIP);
        database.put(nomMachine.getNom(), newItem);
        saveDatabase();
    }

    private void saveDatabase() {
        try {
            List<String> lines = new ArrayList<>();
            for (DnsItem item : database.values()) {
                lines.add(item.getNomMachine() + " " + item.getAdresseIP());
            }
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
