package dns;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {
    // Test de la création d'un DnsItem
    @Test
    public void testDnsItemCreation() {
        NomMachine nomMachine = new NomMachine("www.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.1");
        DnsItem dnsItem = new DnsItem(nomMachine, adresseIP);

        // Vérifie que les attributs sont correctement assignés
        assertEquals(nomMachine, dnsItem.getNomMachine());
        assertEquals(adresseIP, dnsItem.getAdresseIP());
    }

    // Test de la méthode toString de DnsItem
    @Test
    public void testToString() {
        NomMachine nomMachine = new NomMachine("www.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.1");
        DnsItem dnsItem = new DnsItem(nomMachine, adresseIP);

        // Vérifie que la représentation sous forme de chaîne est correcte
        assertEquals("www.example.com 192.168.1.1", dnsItem.toString());
    }
}
