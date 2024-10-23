package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class XifradorAES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "Secreto";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
        "Hola Andrés cómo está tu cuñado",
        "Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage ());
            
            }
            
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        //obtenir els bytes de l'String
        byte[] msgBytes = msg.getBytes("UTF-8");
        
        // Genera IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = sha.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedMessage = cipher.doFinal(msgBytes);

        // Convinar IV i part xifrada
        byte[] encryptedMessageWithIv = new byte[iv.length + encryptedMessage.length];
        System.arraycopy(iv, 0, encryptedMessageWithIv, 0, iv.length);
        System.arraycopy(encryptedMessage, 0, encryptedMessageWithIv, iv.length, encryptedMessage.length);

        // return iv+msgxifrat
        return encryptedMessageWithIv;
    }

    public static String desxifraAES (byte[] bIvIMsgXifrat, String clau) throws Exception{

        // Extreure l'IV (preimers 16 bytes)
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure part xifrada
        byte[] encryptedMessage = new byte[bIvIMsgXifrat.length - MIDA_IV];
        System.arraycopy(bIvIMsgXifrat, MIDA_IV, encryptedMessage, 0, encryptedMessage.length);

        // Fer hash de la clau
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyBytes = sha.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORISME_XIFRAT);

        // Desxifrar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);

        // return String desxifrat
        return new String(decryptedBytes, "UTF-8");
    }

}
