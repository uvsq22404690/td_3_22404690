package dns;

    public class AdresseIP {
        private String ip;

        public AdresseIP(String ip) {
            // vérifier si l'adresse IP est valide 
            if (!isValidIP(ip)) {
                throw new IllegalArgumentException("Adresse IP invalide" + ip); // Exception si l'adresse est invalide
            }
            this.ip = ip;
        }
        // Initiation d'un méthode pour obtenir l'adresse IP
        public String getIp() {
            return ip;
        }

        // Méthode privée pour valider si l'adresse IP est correcte
        private boolean isValidIP(String ip) {
            // Diviser l'adresse IP en segments
            String[] parts = ip.split("\\.");
            if (parts.length != 4) return false; // vérifier que le nombre de segments est 4

            for (String part : parts) {
                int num;
                try {
                    num = Integer.parseInt(part);
                } catch (NumberFormatException e) {
                    return false;
                }
                if (num < 0 || num > 255) return false;
            }
            return true;
        }
        // méthode pour retourner l'adresse IP sous forme de chaine 
        @Override
        public String toString() {
            return ip;
        }
    }


