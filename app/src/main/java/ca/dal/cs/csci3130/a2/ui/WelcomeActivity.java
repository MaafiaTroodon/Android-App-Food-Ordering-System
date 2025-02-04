package ca.dal.cs.csci3130.a2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import ca.dal.cs.csci3130.a2.firebase.FirebaseCRUD;
import ca.dal.cs.csci3130.a2.R;
import android.util.Log;


public class WelcomeActivity extends AppCompatActivity {

    private FirebaseCRUD crud;
    private String emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        this.crud = new FirebaseCRUD();
        this.emailAddress = getIntent().getStringExtra("EMAIL");  // Pass email from MainActivity
        this.showWelcomeMessage();
        this.setupRetrieveCredentialsButton();
    }

    protected void showWelcomeMessage() {
        TextView welcomeLabel = findViewById(R.id.welcomeLabel);  // Ensure this ID exists in XML
        String message = getIntent().getStringExtra(MainActivity.WELCOME_MESSAGE);

        if (message != null && !message.isEmpty()) {
            welcomeLabel.setText(message);
        } else {
            welcomeLabel.setText("Welcome!");  // Default message if intent extra is missing
        }
    }

    protected void retrieveCredentials(View view) {
        Log.d("DEBUG", "Email to retrieve: " + emailAddress); 

        if (emailAddress == null || emailAddress.isEmpty()) {
            Snackbar.make(view, "No email found to retrieve data!", Snackbar.LENGTH_LONG).show();
            return;
        }

        crud.retrieveUser(emailAddress, user -> {
            if (user != null) {
                String message = makeCredentialMessage(user.email, user.password, user.role);
                showRetrievedCredentials(view, message);
            } else {
                Log.e("DEBUG", "User not found in Firebase for email: " + emailAddress);
                Snackbar.make(view, "User not found in the database.", Snackbar.LENGTH_LONG).show();
            }
        });
    }


    protected String makeCredentialMessage(String emailAddress, String passwordHash, String role) {
        return "Email: " + emailAddress + "\nPassword Hash: " + passwordHash + "\nRole: " + role;
    }

    protected void showRetrievedCredentials(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    protected void setupRetrieveCredentialsButton() {
        Button retrieveButton = findViewById(R.id.retrieveFromDBButton);
        retrieveButton.setOnClickListener(this::retrieveCredentials);
    }
}
