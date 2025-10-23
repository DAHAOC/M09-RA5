package iticbcn.xifratge;

import javax.crypto.*;
import javax.crypto.spec.*;

public class XifradorAES implements Xifrador {

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // Ajusta la clave a 16 bytes
            String clauAjustada = String.format("%-16s", clau).substring(0, 16);

            SecretKeySpec key = new SecretKeySpec(clauAjustada.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(msg.getBytes());
            return new TextXifrat(encrypted);
        } catch (Exception e) {
            System.err.println("Error xifrant AES: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            String clauAjustada = String.format("%-16s", clau).substring(0, 16);

            SecretKeySpec key = new SecretKeySpec(clauAjustada.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(xifrat.getBytes());
            return new String(decrypted);
        } catch (Exception e) {
            System.err.println("Error desxifrant AES: " + e.getMessage());
            System.exit(1);
        }
        return null;
    }

}
