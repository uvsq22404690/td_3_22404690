package dns;

public class DnsItem {
    private NomMachine nomMachine; // Nom de la machine
    private AdresseIP adresseIP; // Adresse IP associée à la machine

    // Constructeur pour initialiser un DnsItem avec un nom de machine et une adresse IP
    public DnsItem(NomMachine nomMachine, AdresseIP adresseIP) {
        this.nomMachine = nomMachine; // Assignation du nom de machine
        this.adresseIP = adresseIP; // Assignation de l'adresse IP
    }

    // Méthode pour obtenir le nom de la machine
    public NomMachine getNomMachine() {
        return nomMachine; // Retourne l'objet NomMachine
    }

    // Méthode pour obtenir l'adresse IP
    public AdresseIP getAdresseIP() {
        return adresseIP; // Retourne l'objet AdresseIP
    }

    // Méthode toString pour afficher le DnsItem sous forme de chaîne
    @Override
    public String toString() {
        return nomMachine + " " + adresseIP; // Retourne une représentation textuelle de l'item
    }
}
