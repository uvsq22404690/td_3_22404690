package dns;

import org.junit.Test;
import static org.junit.Assert.*;

public class AdresseIPTest {
    @Test
    public void testValidIP() {
        AdresseIP adresse = new AdresseIP("192.168.0.1");
        assertEquals("192.168.0.1", adresse.getIp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidIP() {
        new AdresseIP("256.100.50.0");
    }
}
