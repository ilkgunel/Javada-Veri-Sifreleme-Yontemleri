/**
 * Created by ilkaygunel on 08/08/15.
 */

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
        import javax.crypto.SecretKeyFactory;
        import javax.crypto.spec.DESedeKeySpec;

public class TripleDESSifreleme
{
    SecretKey key;

    TripleDESSifreleme(byte [] rawkey) throws Exception
    {
        key = readKey(rawkey);
    }

    public  SecretKey readKey(byte[] rawkey) throws Exception
    {
        DESedeKeySpec keyspec = new DESedeKeySpec(rawkey);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
        SecretKey key = keyfactory.generateSecret(keyspec);
        return key;
    }

    public byte[] encrypt(byte[] plain ) throws Exception
    {
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted = cipher.doFinal(plain);
        System.out.println(encrypted);
        return encrypted;
    }
    public byte[] decrypt(byte[] cipher ) throws Exception
    {
        Cipher dcipher = Cipher.getInstance("DESede");
        dcipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = dcipher.doFinal(cipher);
        return decrypted;
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        KeyGenerator keygenerator = KeyGenerator.getInstance("DESede");
        SecretKey myDesKey = keygenerator.generateKey();
        Cipher cipher = Cipher.getInstance("DESede");
        cipher.init(Cipher.ENCRYPT_MODE,myDesKey);
        byte[] encrypted = cipher.doFinal("Turgay".getBytes());
        StringBuffer sifrelenmisBuffer=new StringBuffer();
        for (int i=0;i<encrypted.length;i++)
        {
            sifrelenmisBuffer.append(Integer.toString((encrypted[i] & 0xff) + 0x100, 32));
        }
        System.out.println(sifrelenmisBuffer.toString());

        cipher.init(Cipher.DECRYPT_MODE,myDesKey);
        byte[] textDecrypted = cipher.doFinal(encrypted);
        System.out.println(new String(textDecrypted));
    }
}