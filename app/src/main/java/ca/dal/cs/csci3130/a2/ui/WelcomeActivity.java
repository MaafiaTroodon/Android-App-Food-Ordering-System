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

public class WelcomeActivity extends AppCompatActivity {

    FirebaseCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.showWelcomeMessage();
        this.setupRetrieveCredentialsButton();
        this.crud = new FirebaseCRUD(getDatabase());
    }

    protected void showWelcomeMessage() {
        //incomplete method, add the feature!
    }

    protected FirebaseDatabase getDatabase() {
        //buggy method, fix the bug!
        return null;
    }

    protected String getEmailAddress() {
        //buggy method, fix the bug!
        return null;
    }

    protected String getPasswordHash() {
        //buggy method, fix the bug!
        return null;
    }

    protected String getRole() {
        //buggy method, fix the bug!
        return null;
    }

    protected String makeCredentialMessage(String emailAddress, String passwordHash, String role) {
        return emailAddress + "-" + passwordHash + "-" + role;
    }

    protected void showRetrievedCredentials(View view, String message) {
        //incomplete method, add the feature.
    }

    protected void retrieveCredentials(View view) {
        //incomplete method, add the feature.
    }

    protected void setupRetrieveCredentialsButton() {
        Button retrieveButton = findViewById(R.id.retrieveFromDBButton);
        retrieveButton.setOnClickListener(this::retrieveCredentials);
    }
}