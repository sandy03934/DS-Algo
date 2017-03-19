package practize.helloworld;

/**
 * Created by sinsandi on 10/21/2016.
 */

import example.AESEncryptionDemo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

public class AesCipher {

    private static final String algorithm = "AES/CBC/NoPadding";
//    private static final String algorithm = "AES/CBC/PKCS5Padding";

    private static final byte[] keyValue = padString("sandy03934").getBytes();
    private static final byte[] ivValue = padString("sandy03934").getBytes();
//    private static final byte[] keyValue = new byte[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
//    private static final byte[] ivValue = new byte[] { 'f', 'e', 'd', 'c', 'b', 'a', '9', '8', '7', '6', '5', '4', '3', '2', '1', '0' };

    private static final IvParameterSpec ivspec = new IvParameterSpec(ivValue);
    private static final SecretKeySpec keyspec = new SecretKeySpec(keyValue, "AES");

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String encrypt(String Data) throws Exception {
        Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Cipher c = Cipher.getInstance(algorithm);
        c.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
        byte[] decordedValue =Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    private static String padString(String source) {
        char paddingChar = ' ';
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;

        for (int i = 0; i < padLength; i++)
        {
            source += paddingChar;
        }
        return source;
    }

    public static void main(String[] args) throws Exception {

//        String password = "Welcome100$123456789";
//        String passwordEnc = AesCipher.encrypt(padString(password));
//        String passwordDec = AesCipher.decrypt(passwordEnc);
//
//        System.out.println("Plain Text : " + password);
//        System.out.println("Encrypted Text : " + passwordEnc);
//        System.out.println("Decrypted Text : " + passwordDec);

        AESEncryptionDemo encryptionDemo = new AESEncryptionDemo();
        String password = "Welcome99$";
        String key = "sandip.03934";
        String passwordEnc = encryptionDemo.encrypt(password, key);
        String passwordDec = encryptionDemo.decrypt(passwordEnc, key);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);

//        System.out.println(Arrays.toString("0123456789ABCDEF".getBytes()));
//        System.out.println(hexArray);
//        System.out.println(bytesToHex(keyValue));
//        System.out.println(bytesToHex("0123456789ABCDEF".getBytes()));
//
//        byte [] output = toByteArray("0123456789ABCDEF");
//        System.out.println(output);
    }


    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }



}
