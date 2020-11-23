package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity{
    private static final String TAG = "CreateActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_role = "role";

    private EditText editName;
    private EditText editEmail;
    private String role;

   final private FirebaseFirestore db = FirebaseFirestore.getInstance();

    Button login;
    TextView signUp;
    FirebaseAuth auth;
    FirebaseUser user;
    EditText passwrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_create);

        auth = FirebaseAuth.getInstance();
        editName = findViewById(R.id.enterName);
        editEmail = findViewById(R.id.emailAddress);
        passwrd = findViewById(R.id.passwordd);
        signUp = findViewById(R.id.login_btn);
        login = findViewById(R.id.loginnn);

        //Spinner setup
        Spinner spinner =  findViewById(R.id.role_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get user info
                String name = editName.getText().toString();
                String email = editEmail.getText().toString();
                String smot = passwrd.getText().toString();

                // validate
                if(email.isEmpty()){
                    editEmail.setError("Please enter a valid email address.");
                    editEmail.requestFocus();
                } else if (smot.isEmpty()){
                    passwrd.setError("Please enter a password.");
                    passwrd.requestFocus();
                } else if(name.isEmpty()){
                    editName.setError("Please enter a name.");
                    editName.requestFocus();
                }
                else if (smot.length()<6) {
                    editEmail.setError("Please enter  with at least 7 characters.");
                }
                else{

                    // once validated, login
                    auth.createUserWithEmailAndPassword(email, smot)
                            .addOnCompleteListener(CreateActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(CreateActivity.this, "An error occurred...\nplease revise your info", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(CreateActivity.this, CreateActivity.class));
                                    } else{
                                        // User name :
                                        String name = ((TextView) findViewById(R.id.enterName)).getText().toString();


                                        if (name.equals(null) || name.equals("")){
                                            Toast.makeText(CreateActivity.this, "An error occurred...\nname is missing???", Toast.LENGTH_SHORT).show();
                                        }

                                        // User role
                                        Spinner spinner =findViewById(R.id.role_spinner);
                                        String text = spinner.getSelectedItem().toString();
                                        if (text.equals(spinner.getItemAtPosition(2))){
                                            role = "employee";
                                            name = name + "/e";
                                        } else if (text.equals(spinner.getItemAtPosition(1))){
                                            role = "client";
                                            name = name + "/c";
                                        }
                                        user = auth.getCurrentUser();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(editName.getText().toString()) // change to name
                                                .build();
                                        user.updateProfile(profileUpdates);

                                        saveNote();
                                        if(user.getEmail().equals("admin@novigrad.com")){
                                            startActivity(new Intent(CreateActivity.this, AdminActivity.class));
                                        } else {
                                        Toast.makeText(CreateActivity.this, "You succesfully created an account.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(CreateActivity.this, HomeActivity.class));
                                    }
                                    }
                                }
                            });
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateActivity.this, EmailActivity.class));
            }
        });
    }

    public void saveNote(){
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_NAME, name);
        note.put(KEY_EMAIL, email);
        note.put(KEY_role, role);

        db.collection("users").add(note)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreateActivity.this, "SUCCESS!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateActivity.this, "AAARG!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
}