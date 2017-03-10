package business;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Helper to handle generic methods
 */
public class Util {

    /**
     * Singleton instance
     */
    private static Util instance;

    /**
     * Empty constructor for singleton
     */
    private Util() {
    }

    /**
     * Get the unique instance of the Util
     *
     * @return An instance of Util
     */
    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    /**
     * Hash a string with SHA-256 method
     *
     * @param stringToEncrypt A string to encrypt
     * @return The string encrypted by SHA-256 (maybe empty if there is a problem)
     */
    public String hashString(String stringToEncrypt) {
        String encryptedString = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(stringToEncrypt.getBytes());
            encryptedString = Base64.getEncoder().encodeToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedString;
    }
}
