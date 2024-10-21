package dns;

public class NomMachine {
    private String nom;

    // Constructeur qui valide le nom de la machine lors de l'initialisation
    public NomMachine(String nom) {
        if (nom == null || nom.isEmpty() || !isValidNom(nom)) {
            throw new IllegalArgumentException("Nom de machine invalide");
        }
        this.nom = nom;
    }

    // Méthode pour récupérer le nom de la machine
    public String getNom() {
        return nom;
    }

    // Méthode privée pour valider le nom de la machine
    private boolean isValidNom(String nom) {
        // Logique pour valider le nom de la machine
        return nom.matches("^[a-zA-Z0-9\\-]+(\\.[a-zA-Z0-9\\-]+)+$");
    }

    // Représentation sous forme de chaîne du nom de la machine
    @Override
    public String toString() {
        return nom;
    }
}
