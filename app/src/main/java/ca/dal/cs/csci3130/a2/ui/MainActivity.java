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
import androidx.test.espresso.idling.CountingIdlingResource;

import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;
import ca.dal.cs.csci3130.a2.validator.CredentialValidator;
import ca.dal.cs.csci3130.a2.firebase.FirebaseCRUD;
import ca.dal.cs.csci3130.a2.R;
import ca.dal.cs.csci3130.a2.util.PasswordUtility;
import com.google.firebase.FirebaseApp;
import android.util.Log;
import androidx.test.espresso.idling.CountingIdlingResource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.cs.csci3130.a2.welcome";
    private FirebaseCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_main);
        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.crud = new FirebaseCRUD();
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

    protected void move2WelcomeActivity(String welcomeMessage, String emailAddress) {
        Log.d("DEBUG", "Moving to WelcomeActivity with email: " + emailAddress);
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra(WELCOME_MESSAGE, welcomeMessage);
        intent.putExtra("EMAIL", emailAddress);
        startActivity(intent);
        finish();
    }

    protected String getWelcomeMessage(String emailAddress, String role) {
        return "Hi there! Your role is: " + role + ". A welcome email was sent to " + emailAddress + ".";
    }




    public static final CountingIdlingResource idlingResource = new CountingIdlingResource("REGISTRATION");

    @Override
    public void onClick(View view) {
        String emailAddress = getEmailAddress();
        String password = getPassword();
        String role = getRole();

        CredentialValidator validator = new CredentialValidator();

        if (emailAddress.isEmpty()) {
            setStatusMessage("Email address cannot be empty.");
        } else if (!validator.isValidEmailAddress(emailAddress)) {
            setStatusMessage("Invalid email address format.");
        } else if (!validator.isDALEmailAddress(emailAddress)) {
            setStatusMessage("Email must be a Dalhousie email.");
        } else if (!validator.isValidPassword(password)) {
            setStatusMessage("Invalid password format.");
        } else if (!validator.isValidRole(role)) {
            setStatusMessage("Invalid role selection.");
        } else {
            idlingResource.increment(); // 🚀 Mark as busy
            crud.saveUser(emailAddress, password, role);
            move2WelcomeActivity(getWelcomeMessage(emailAddress, role), emailAddress);
            idlingResource.decrement(); // ✅ Mark as idle
        }
    }




    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }
}
