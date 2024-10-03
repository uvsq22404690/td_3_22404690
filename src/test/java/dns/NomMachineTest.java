package dns;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {
    @Test
    public void testValidNom() {
        NomMachine nom = new NomMachine("www.example.com");
        assertEquals("www.example.com", nom.getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomNull() {
        new NomMachine(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomEmpty() {
        new NomMachine("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomFormat() {
        new NomMachine("invalid_name");
    }
}
