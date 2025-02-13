package ca.dal.cs.csci3130.a2.firebase;

import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import ca.dal.cs.csci3130.a2.util.PasswordUtility; // ✅ Import PasswordUtility

public class FirebaseCRUD {
    private final DatabaseReference databaseReference;

    public FirebaseCRUD() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.databaseReference = database.getReference("Users");
    }

    public void saveUser(String email, String password, String role) {
        String hashedPassword = PasswordUtility.makeHash(password); // ✅ Now using PasswordUtility
        User user = new User(email, hashedPassword, role);
        databaseReference.child(email.replace(".", "_")).setValue(user);
    }

    public void retrieveUser(String email, final UserCallback callback) {
        databaseReference.child(email.replace(".", "_")).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                callback.onCallback(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                callback.onCallback(null);
            }
        });
    }

    public interface UserCallback {
        void onCallback(User user);
    }

    public static class User {
        public String email;
        public String password;
        public String role;

        public User() {}

        public User(String email, String password, String role) {
            this.email = email;
            this.password = password;
            this.role = role;
        }
    }
}
