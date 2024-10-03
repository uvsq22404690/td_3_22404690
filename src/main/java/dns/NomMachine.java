package dns;

public class NomMachine {
    private String nom;

    public NomMachine(String nom) {
        if (nom == null || nom.isEmpty() || !isValidNom(nom)) {
            throw new IllegalArgumentException("Nom de machine invalide");
        }
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    private boolean isValidNom(String nom) {
        // Logique pour valider le nom de la machine
        return nom.matches("^[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)+$");
    }

    @Override
    public String toString() {
        return nom;
    }
}
