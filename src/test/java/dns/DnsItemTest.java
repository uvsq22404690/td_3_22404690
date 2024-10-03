package dns;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {
    @Test
    public void testDnsItemCreation() {
        NomMachine nomMachine = new NomMachine("www.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.1");
        DnsItem dnsItem = new DnsItem(nomMachine, adresseIP);

        assertEquals(nomMachine, dnsItem.getNomMachine());
        assertEquals(adresseIP, dnsItem.getAdresseIP());
    }

    @Test
    public void testToString() {
        NomMachine nomMachine = new NomMachine("www.example.com");
        AdresseIP adresseIP = new AdresseIP("192.168.1.1");
        DnsItem dnsItem = new DnsItem(nomMachine, adresseIP);

        assertEquals("www.example.com 192.168.1.1", dnsItem.toString());
    }
}
