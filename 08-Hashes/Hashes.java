import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public long npass = 0;

    private static final char[] charset = ("abcdefABCDEF1234567890!").toCharArray();
    private static final int CHARSET_SIZE = charset.length;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!"; 
        Hashes h = new Hashes();

        String[] aHashes = {
                h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("======== \n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf(" --\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
          
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("\n\n");
        }
    }

    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digest = md.digest((salt + pw).getBytes("UTF-8"));
            return bytesToHex(digest);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iterations = 65536;
            int keyLength = 128;

            char[] passwordChars = pw.toCharArray();
            byte[] saltBytes = salt.getBytes("UTF-8");

            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] key = skf.generateSecret(spec).getEncoded();

            return bytesToHex(key);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    
    public String forcaBruta(String alg, String hashObjectiu, String salt) throws Exception {
        npass = 0;
        int len = 6; 
        
        int[] idx = new int[len];
        Arrays.fill(idx, 0); 

        char[] candidateChars = new char[len];
        String candidate;

        
        while (true) {
            
            for (int i = 0; i < len; i++) {
                candidateChars[i] = charset[idx[i]];
            }
            candidate = new String(candidateChars);

            npass++; 

           
            String h = (alg.equalsIgnoreCase("SHA-512")) 
                    ? getSHA512AmbSalt(candidate, salt)
                    : getPBKDF2AmbSalt(candidate, salt);

            if (h.equals(hashObjectiu)) {
                return candidate; 
            }

           
            int position = len - 1;
            while (position >= 0) {
                idx[position]++;
                if (idx[position] < CHARSET_SIZE) { 
                    break; 
                } else {
                    idx[position] = 0; 
                    position--;
                }
            }

            if (position < 0) {
                break; 
            }
        }
        return null; 
    }

   

    private static String bytesToHex(byte[] bytes) {
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes).toLowerCase();
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        if (diff < 0) {
            diff = 0;
        }

        long millis = diff % 1000;
        long totalSeconds = diff / 1000;
        long seconds = totalSeconds % 60;
        long totalMinutes = totalSeconds / 60;
        long minutes = totalMinutes % 60;
        long totalHours = totalMinutes / 60;
        long hours = totalHours % 24;
        long days = totalHours / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis",
                days, hours, minutes, seconds, millis);
    }
}