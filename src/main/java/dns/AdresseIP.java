package dns;

    public class AdresseIP {
        private String ip;

        public AdresseIP(String ip) {
            if (!isValidIP(ip)) {
                throw new IllegalArgumentException("Adresse IP invalide");
            }
            this.ip = ip;
        }

        public String getIp() {
            return ip;
        }

        private boolean isValidIP(String ip) {
            // Logique pour valider l'adresse IP
            String[] parts = ip.split("\\.");
            if (parts.length != 4) return false;

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

        @Override
        public String toString() {
            return ip;
        }
    }


