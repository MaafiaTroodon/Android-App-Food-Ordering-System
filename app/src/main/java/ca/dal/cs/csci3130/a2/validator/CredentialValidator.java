package ca.dal.cs.csci3130.a2.validator;

import androidx.core.util.PatternsCompat;

import java.util.regex.Pattern;

public class CredentialValidator {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#])[A-Za-z\\d!@#]{7,}$";

    public static boolean isEmptyEmailAddress(String emailAddress) {
        return emailAddress.trim().isEmpty();
    }

    public static boolean isValidEmailAddress(String emailAddress) {
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

    public static boolean isDALEmailAddress(String emailAddress) {
        return emailAddress.toLowerCase().matches("^[a-zA-Z0-9._%+-]+@dal\\.ca$");
    }

    public static boolean isValidPassword(String password) {
        // Password must have at least 7 characters, 1 digit, and at least 2 special characters (!@#)
        String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#].*[!@#])[A-Za-z\\d!@#]{7,}$";
        return password.matches(PASSWORD_REGEX);
    }


    public static boolean isValidRole(String role) {
        return !role.equals("Select your role");
    }
}