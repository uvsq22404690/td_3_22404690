package dns;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DnsTest {
    private Dns dns;

    @Before
    public void setUp() throws IOException {
        // Création d'un fichier temporaire pour les tests
        Path testFilePath = Paths.get("test-dns.txt");
        List<String> initialData = new ArrayList<>();
        initialData.add("www.example.com 192.168.1.1");
        initialData.add("api.example.com 192.168.1.2");


        Files.write(testFilePath, initialData, StandardCharsets.UTF_8);

        dns = new Dns(testFilePath.toString());
    }

    @Test
    public void testGetItemByNom() {
        DnsItem item = dns.getItemByNom("www.example.com");
        assertNotNull(item);
        assertEquals("192.168.1.1", item.getAdresseIP().toString());
    }

    @Test
    public void testGetItemByAdresse() {
        DnsItem item = dns.getItemByAdresse("192.168.1.2");
        assertNotNull(item);
        assertEquals("api.example.com", item.getNomMachine().getNom());
    }

    @Test
    public void testGetItems() {
        List<DnsItem> items = dns.getItems("example.com");
        assertEquals(2, items.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemAlreadyExists() {
        NomMachine nomMachine = new NomMachine("www.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.3");
        dns.addItem(nomMachine, adresseIP);
    }

    @Test
    public void testAddItem() {
        NomMachine nomMachine = new NomMachine("new.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.4");
        dns.addItem(nomMachine, adresseIP);

        DnsItem item = dns.getItemByNom("new.example.com");
        assertNotNull(item);
        assertEquals("192.168.1.4", item.getAdresseIP().toString());
    }
}
