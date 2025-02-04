package ca.dal.cs.csci3130.a2.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseCRUD {
    private final FirebaseDatabase database;
    private DatabaseReference emailRef = null;
    private String extractedEmailAddress;

    public FirebaseCRUD(FirebaseDatabase database) {
        this.database = database;
        //incomplete method, initialize the database references and listeners!
    }

    protected DatabaseReference getEmailAddressRef() {
        return this.database.getReference("emailAddress");
    }

    public void setEmailAddress(String emailAddress) {
        this.emailRef.setValue(emailAddress);
    }

    protected void setEmailListener() {
        this.emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                extractedEmailAddress = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    protected void initializeDatabaseRefs() {
        //incomplete method, get more database references!
        this.emailRef = getEmailAddressRef();
    }

    protected void initializeDatabaseRefListeners() {
        //incomplete method, set more listeners!
        this.setEmailListener();
    }

    public String getExtractedEmailAddress() {
        return this.extractedEmailAddress;
    }

}
