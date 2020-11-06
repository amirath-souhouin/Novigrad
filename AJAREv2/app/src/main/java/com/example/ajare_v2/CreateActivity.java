package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateActivity extends AppCompatActivity{

    EditText emailAddress, passwordd, username;
    Button login;
    TextView signUp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_create);


        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.enterName);
        emailAddress = findViewById(R.id.emailAddress);
        passwordd = findViewById(R.id.passwordd);
        signUp = findViewById(R.id.login_btn);
        login = findViewById(R.id.loginnn);


        //Spinner setup
        Spinner spinner = (Spinner) findViewById(R.id.role_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get user info
                String email = emailAddress.getText().toString();
                String smot = passwordd.getText().toString();

                // validate
                if(email.isEmpty()){
                    emailAddress.setError("Please enter a valid email address.");
                    emailAddress.requestFocus();
                } else if (smot.isEmpty()){
                    passwordd.setError("Please enter a password.");
                    passwordd.requestFocus();
                } else if(smot.isEmpty() && email.isEmpty()){
                    Toast.makeText(CreateActivity.this, "Fields are empty!!", Toast.LENGTH_SHORT).show();
                } else if(!(smot.isEmpty() && email.isEmpty())){

                    // once validated, login
                    auth.createUserWithEmailAndPassword(email, smot)
                            .addOnCompleteListener(CreateActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(CreateActivity.this, "An error occurred...", Toast.LENGTH_SHORT).show();
                                    } else{

                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                        // User name :
                                        String name = findViewById(R.id.enterName).toString();
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName("bla") // change to name
                                                .build();
                                        user.updateProfile(profileUpdates);

                                        // User role
                                        Spinner spinner = (Spinner)findViewById(R.id.role_spinner);
                                        String text = spinner.getSelectedItem().toString();
                                        if (text.equals(spinner.getItemAtPosition(2))){
                                            startActivity(new Intent(CreateActivity.this, EmployeeActivity.class));

                                        }else {
                                            startActivity(new Intent(CreateActivity.this, HomeActivity.class));
                                        }
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(CreateActivity.this, "Oh no! An error has occurred...", Toast.LENGTH_SHORT).show();
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
}