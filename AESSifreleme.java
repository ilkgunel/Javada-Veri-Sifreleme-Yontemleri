/**
 * Created by ilkaygunel on 08/08/15.
 */
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AESSifreleme {
    static String plaintext = "Turgay";
    static String encryptionKey = "0123456789abcdef";
    public static void main(String [] args) {
        try {

            byte[] cipher = encrypt(plaintext, encryptionKey);

            System.out.print("cipher:  ");
            for (int i=0; i<cipher.length; i++)
                System.out.print(new Integer(cipher[i])+" ");
            System.out.println("");

            StringBuffer sb32 = new StringBuffer();

            for (int i = 0; i < cipher.length; i++)
            {
                sb32.append(Integer.toString((cipher[i] & 0xff) + 0x100, 32));
            }
            System.out.println("cipher:  "+sb32.toString());

            String decrypted = decrypt(cipher, encryptionKey);

            System.out.println("decrypt: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }
}
