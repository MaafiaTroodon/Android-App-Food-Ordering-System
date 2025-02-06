package ca.dal.cs.csci3130.a2.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtility {

    public static String makeHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
