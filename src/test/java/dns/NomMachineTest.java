package dns;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {
    @Test
    public void testValidNom() {
        // Test de création d'un NomMachine valide
        NomMachine nom = new NomMachine("www.example.com");
        assertEquals("www.example.com", nom.getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomNull() {
        // Test de création d'un NomMachine avec nom null
        new NomMachine(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomEmpty() {
        // Test de création d'un NomMachine avec nom vide
        new NomMachine("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNomFormat() {
        // Test de création d'un NomMachine avec format invalide
        new NomMachine("invalid_name");
    }
}
