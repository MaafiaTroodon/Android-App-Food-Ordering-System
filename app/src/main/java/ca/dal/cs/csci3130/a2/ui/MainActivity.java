package ca.dal.cs.csci3130.a2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ca.dal.cs.csci3130.a2.validator.CredentialValidator;
import ca.dal.cs.csci3130.a2.firebase.FirebaseCRUD;
import ca.dal.cs.csci3130.a2.R;
import ca.dal.cs.csci3130.a2.util.PasswordUtility;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";
    FirebaseCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.crud = new FirebaseCRUD(getDatabase());
    }

    public void loadRoleSpinner() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        List<String> roles = new ArrayList<>();
        roles.add("Select your role");
        roles.add("Buyer");
        roles.add("Seller");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, roles);
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        roleSpinner.setAdapter(spinnerAdapter);
    }

    public void setupRegistrationButton() {
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    protected FirebaseDatabase getDatabase() {
        //buggy method, fix the bug!
        return null;
    }

    protected String getEmailAddress() {
        EditText emailBox = findViewById(R.id.emailBox);
        return emailBox.getText().toString().trim();
    }

    protected String getPassword() {
        EditText passwordBox = findViewById(R.id.passwordBox);
        return passwordBox.getText().toString().trim();
    }

    protected String getRole() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        return roleSpinner.getSelectedItem().toString().trim();
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected String getWelcomeMessage(String emailAddress, String role) {
        //buggy method, fix the bug!
        return null;
    }

    protected void move2WelcomeActivity(String welcomeMessage) {
        //incomplete method, add the feature!
    }

    protected void saveCredentials(String emailAddress, String password, String role) {
        //Incomplete method, add the feature.
    }

    @Override
    public void onClick(View view) {
        String emailAddress = getEmailAddress();
        String password = getPassword();
        String role = getRole();

        if (emailAddress.isEmpty()) {
            setStatusMessage(getResources().getString(R.string.EMPTY_EMAIL_ADDRESS));
        } else if (!CredentialValidator.isValidEmailAddress(emailAddress)) {
            setStatusMessage(getResources().getString(R.string.INVALID_EMAIL_ADDRESS));
        } else if (!CredentialValidator.isDALEmailAddress(emailAddress)) {
            setStatusMessage(getResources().getString(R.string.INVALID_DAL_EMAIL));
        } else if (!CredentialValidator.isValidPassword(password)) {
            setStatusMessage(getResources().getString(R.string.INVALID_PASSWORD));
        } else if (!CredentialValidator.isValidRole(role)) {
            setStatusMessage(getResources().getString(R.string.INVALID_ROLE));
        } else {
            // ✅ If everything is valid, clear the status message
            setStatusMessage("");

            // Proceed to next step (e.g., registration logic)
            move2WelcomeActivity(getWelcomeMessage(emailAddress, role));
        }
    }

}