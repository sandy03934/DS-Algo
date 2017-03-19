package practize.helloworld;

import example.AESEncryptionDemo;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * Created by sinsandi on 10/3/2016.
 */
public class AESencrypt {

        private static final String ALGO = "AES";
//        private static final byte[] keyValue =
//                new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
//                        'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
        private static final String keyValue = "553c56e8-f9d7-46bb-9760-d705a39fbcc1";

        public static String encrypt(String Data) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(Data.getBytes());
//            String encryptedValue = new String(encVal);
            String encryptedValue = new BASE64Encoder().encode(encVal);
            return encryptedValue;
        }

        public static String decrypt(String encryptedData) throws Exception {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        }
        private static Key generateKey() throws Exception {
//            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");
//            SecretKeySpec keyspec = new SecretKeySpec(keyValue.getBytes(), ALGO);
//            Key key = keyFactory.generateSecret(keyspec);


            byte[] shaKey = keyValue.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            shaKey = sha.digest(shaKey);
            System.out.println(shaKey);
            System.out.println(new BASE64Encoder().encode(shaKey));
            shaKey = Arrays.copyOf(shaKey, 16);

//            int keyoffset = keyValue.length() > 16 ? 16 : keyValue.length();
            Key key = new SecretKeySpec(shaKey, 0, 16, ALGO);
            return key;
        }

    public static void main(String[] args) throws Exception {

        String password = "Welcome99";
//        String passwordEnc = AESencrypt.encrypt(password);
//        String passwordDec = AESencrypt.decrypt(passwordEnc);

//        System.out.println("Plain Text : " + password);
//        System.out.println("Encrypted Text : " + passwordEnc);
//        System.out.println("Decrypted Text : " + passwordDec);

        AESEncryptionDemo example = new AESEncryptionDemo();
        System.out.println(example.encrypt("Password@MoreThanMaximumCharactersWhichis40", "a2d89659-8386-4fd2-b624-b085b3478866") + ":" + "13ad0d74-c5df-4668-bb8e-c30e5ce0956e".toUpperCase());

//        AESencrypt aes = new AESencrypt();
//        String passwordEnc = aes.encrypt(password, keyValue);
//        String passwordDec = aes.decrypt(passwordEnc, keyValue);
//        System.out.println("Plain Text : " + password);
//        System.out.println("Encrypted Text : " + passwordEnc);
//        System.out.println("Decrypted Text : " + passwordDec);
    }

    private final String characterEncoding = "UTF-8";
//    private final String cipherTransformation = "AES/CBC/PKCS5Padding";
    private final String cipherTransformation = "AES/CBC/NoPadding";
    private final String aesEncryptionAlgorithm = "AES";

    public  byte[] decrypt(byte[] cipherText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpecy = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpecy, ivParameterSpec);
        cipherText = cipher.doFinal(cipherText);
        return cipherText;
    }

    public byte[] encrypt(byte[] plainText, byte[] key, byte [] initialVector) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Cipher cipher = Cipher.getInstance(cipherTransformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initialVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        plainText = cipher.doFinal(plainText);
        return plainText;
    }

    private byte[] getKeyBytes(String key) throws UnsupportedEncodingException{
        byte[] keyBytes= new byte[16];
        byte[] parameterKeyBytes= key.getBytes();
        System.arraycopy(parameterKeyBytes, 0, keyBytes, 0, Math.min(parameterKeyBytes.length, keyBytes.length));
        return keyBytes;
    }

    /// <summary>
    /// Encrypts plaintext using AES 128bit key and a Chain Block Cipher and returns a base64 encoded string
    /// </summary>
    /// <param name="plainText">Plain text to encrypt</param>
    /// <param name="key">Secret key</param>
    /// <returns>Base64 encoded string</returns>
    public String encrypt(String plainText, String key) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] plainTextbytes = plainText.getBytes(characterEncoding);
        byte[] keyBytes = getKeyBytes(key);
        return Base64.getEncoder().encodeToString(encrypt(plainTextbytes,keyBytes, keyBytes));
    }

    /// <summary>
    /// Decrypts a base64 encoded string using the given key (AES 128bit key and a Chain Block Cipher)
    /// </summary>
    /// <param name="encryptedText">Base64 Encoded String</param>
    /// <param name="key">Secret Key</param>
    /// <returns>Decrypted String</returns>
    public String decrypt(String encryptedText, String key) throws KeyException, GeneralSecurityException, GeneralSecurityException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException {
        byte[] cipheredBytes = Base64.getDecoder().decode(encryptedText);
        byte[] keyBytes = getKeyBytes(key);
        return new String(decrypt(cipheredBytes, keyBytes, keyBytes), characterEncoding);
    }
}
