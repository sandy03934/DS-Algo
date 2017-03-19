package example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sandip Singh.
 */
public class AESEncryptionDemo {

    /**
     * Logger for trace and errors.
     */
    private static final Logger LOGGER = Logger.getLogger(AESEncryptionDemo.class.getName());

    /**
     * Offset for key ranging more than 16 characters.
     */
    private static final int KEY_OFFSET = 16;

    /**
     * Encryption Algo.
     */
    public static final String ENCRYPTION_ALGO = "AES/CBC/NoPadding";

    /**
     * Encrypts the value with the given token string as key.
     * @param value    String value which needs to be encrypted.
     * @param token    String value which acts as symmetric key.
     * @return    The encrypted string.
     */
    public String encrypt(String value, String token) {
        String encryptedValue = null;
        IvParameterSpec ivspec = new IvParameterSpec(adjustKey(token).getBytes());
        Key key = new SecretKeySpec(adjustKey(token).getBytes(), "AES");
        try {
            Cipher c = Cipher.getInstance(ENCRYPTION_ALGO);
            c.init(Cipher.ENCRYPT_MODE, key, ivspec);
            byte[] encVal = c.doFinal(padString(value).getBytes());
            encryptedValue = Base64.getEncoder().encodeToString(encVal);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "Invalid decryption algorithm used.", e);
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "Invalid key used for decrypting ", e);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "Invalid padding ", e);
        }
        return encryptedValue;
    }

    public String decrypt(String value, String token) {
        String decryptedVal = null;
        IvParameterSpec ivspec = new IvParameterSpec(adjustKey(token).getBytes());
        Key key = new SecretKeySpec(adjustKey(token).getBytes(), "AES");
        try {
            Cipher c = Cipher.getInstance(ENCRYPTION_ALGO);
            c.init(Cipher.DECRYPT_MODE, key, ivspec);
            byte[] decodedValue = Base64.getDecoder().decode(value);
            byte[] decryptedValue = c.doFinal(decodedValue);
            decryptedVal = new String(decryptedValue).trim();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            LOGGER.log(Level.SEVERE, "Invalid decryption algorithm used.", e);
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "Invalid key used for decrypting ", e);
        } catch (BadPaddingException | IllegalBlockSizeException e) {
            LOGGER.log(Level.SEVERE, "Invalid padding ", e);
        }
        return decryptedVal;
    }

    /**
     * Generates a key of length 16 in case the provided token is greater than 16 character in length else it returns
     * the token itself.
     *
     * @param token The token or the key parameter with which the password gets encrypted.
     * @return Key of type {@link String}.
     */
    private String adjustKey(String token) {
        return isNoneEmpty(token) && token.length() > KEY_OFFSET ? token.substring(0, KEY_OFFSET) : padString(token);
    }

    /**
     * Checks whether a given string is empty.
     * @param str   source string.
     * @return  Boolean value.
     */
    private boolean isNoneEmpty(String str) {
        return (str != null && str.trim().length() > 0);
    }

    /**
     * Pad the source string with space and makes it equal to the length of the KEY_OFFSET which is 16.
     *
     * @param source Source string to be padded.
     * @return padded string.
     */
    private String padString(String source) {
        char paddingChar = ' ';
        int x = source.length() % KEY_OFFSET;
        int padLength = KEY_OFFSET - x;
        StringBuilder builder = new StringBuilder(KEY_OFFSET);
        builder.append(source);
        for (int i = 0; i < padLength; i++) {
            builder.append(paddingChar);
        }
        return builder.toString();
    }
}
