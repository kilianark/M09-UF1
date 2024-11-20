import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    
    private int npass = 0;
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaaa";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
        System.out.printf("===========================\n");
        System.out.printf("Algorisme: %s\n", algorismes[i]);
        System.out.printf("Hash: %s\n",aHashes[i]);
        System.out.printf("---------------------------\n");
        System.out.printf("-- Inici de força bruta ---\n");
        
        long t1 = System.currentTimeMillis();
        pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
        long t2 = System.currentTimeMillis();
        
        System.out.printf("Pass : %s\n", pwTrobat);
        System.out.printf("Provats: %d\n", h.npass);
        System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
        System.out.printf("---------------------------\n\n");
        }
        }

    public String getSHA512AmbSalt(String pw, String salt) throws  Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), 10000, 512);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String charset = "abcdefABCDEF1234567890!";
        char[] password = new char[6];
        npass = 0;

        // Intentar todas las combinaciones posibles de longitud 1 a 6
        for (int length = 1; length <= 6; length++) {
            if (forcaBrutaRecursiva(alg, hash, salt, password, 0, length, charset)) {
                return new String(password, 0, length);
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours % 24, minutes % 60, seconds % 60, millis % 1000);

    }

    private boolean forcaBrutaRecursiva(String alg, String hash, String salt, char[] password, int pos, int length, String charset) throws Exception {
        if (pos == length) {

            if (npass % 1000 == 0) {
                System.out.printf("Probant combinació #%d: %s\n", npass, new String(password, 0, length));
            }

            npass++;
            String generatedHash = alg.equals("SHA-512") ? getSHA512AmbSalt(new String(password, 0, length), salt) :
                                  getPBKDF2AmbSalt(new String(password, 0, length), salt);
            return generatedHash.equals(hash);
        }

        for (char c : charset.toCharArray()) {
            password[pos] = c;
            if (forcaBrutaRecursiva(alg, hash, salt, password, pos + 1, length, charset)) {
                return true;
            }
        }
        return false;
    }
}