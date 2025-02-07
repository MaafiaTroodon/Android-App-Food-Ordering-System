package ca.dal.cs.csci3130.a2.validator;

import androidx.core.util.PatternsCompat;

public class CredentialValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#])[A-Za-z\\d!@#]{7,}$";

    public static boolean isEmptyEmailAddress(String emailAddress) {
        return emailAddress.trim().isEmpty();
    }

    public boolean isValidEmailAddress(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }


    public boolean isDALEmailAddress(String email) {
        return email.endsWith("@dal.ca");
    }




    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }




    public static boolean isValidRole(String role) {
        return role.equals("Buyer") || role.equals("Seller");
    }

    // Moved message formatting logic here
    public static String formatCredentials(String emailAddress, String passwordHash, String role) {
        return "Email: " + emailAddress + "\n" +
                "Password Hash: " + passwordHash + "\n" +
                "Role: " + role;
    }
}
