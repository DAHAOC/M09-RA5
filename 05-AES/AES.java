import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // 1. Obtener los bytes del mensaje
        byte[] msgBytes = msg.getBytes("UTF-8");

        // 2. Generar el hash SHA-256 de la clave para obtener una clave de 256 bits (32 bytes)
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH); // "SHA-256"
        byte[] clauBytes = md.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT); // "AES"

        // 3. Generar un Vector de Inicialización (IV) aleatorio
        iv = new byte[MIDA_IV]; // MIDA_IV es 16
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // 4. Crear e inicializar el cifrador (Cipher)
        Cipher aesCipher = Cipher.getInstance(FORMAT_AES); // "AES/CBC/PKCS5Padding"
        aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // 5. Cifrar el mensaje
        byte[] msgXifrat = aesCipher.doFinal(msgBytes);

        // 6. Combinar el IV y el mensaje cifrado en un solo array de bytes
        byte[] ivIMsgXifrat = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(iv, 0, ivIMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, ivIMsgXifrat, MIDA_IV, msgXifrat.length);

        return ivIMsgXifrat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // 1. Extraer el IV (los primeros 16 bytes)
        byte[] ivBytes = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 2. Extraer el mensaje cifrado (el resto del array)
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // 3. Generar la misma clave que se usó para cifrar (con el hash SHA-256)
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = md.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // 4. Crear e inicializar el cifrador en modo de descifrado
        Cipher aesCipher = Cipher.getInstance(FORMAT_AES);
        aesCipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // 5. Descifrar el mensaje
        byte[] msgDesxifratBytes = aesCipher.doFinal(msgXifrat);

        // 6. Convertir los bytes descifrados a un String
        return new String(msgDesxifratBytes, "UTF-8");
    }

    public static void main(String[] args) {
        String[] msgs = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado", "Àgora illa Ôtto"};
        String msg = "";
        byte[] bXifrats = null;
        String desxifrat = "";

        for (int i = 0; i < msgs.length; i++) {
            msg = msgs[i];
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
        }

        System.out.println("-------------------");
        System.out.println("Msg: " + msg);
        System.out.println("Enc: " + new String(bXifrats));
        System.out.println("DEC: " + desxifrat);
    }
}
