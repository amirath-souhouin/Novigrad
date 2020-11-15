package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class EmailActivity extends AppCompatActivity{

    private static final String TAG = "EmailPassword";

    EditText emailAddress, passwordd;
    Button login;
    TextView signUp;
    FirebaseAuth auth;
 
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        auth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.emailAddress);
        passwordd = findViewById(R.id.passworddd);
        signUp = findViewById(R.id.create_btn);
        login = findViewById(R.id.loginnn);
        user = FirebaseAuth.getInstance().getCurrentUser();

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
                    passwordd.setError("Please enter a valid password.");
                    passwordd.requestFocus();
                } else if(smot.length()<6){
                    Toast.makeText(EmailActivity.this, "Enter a password with at least 7 characters", Toast.LENGTH_SHORT).show();
                } else{

                    // once validated, login
                    auth.signInWithEmailAndPassword(email, smot)
                            .addOnCompleteListener(EmailActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(EmailActivity.this, "An error occurred...", Toast.LENGTH_SHORT).show();
                            } else{
                                if(user.getEmail().equals("admin@uottawa.ca")){
                                    startActivity(new Intent(EmailActivity.this, AdminActivity.class));
                                } else {
                                    Toast.makeText(EmailActivity.this, "You succesfully logged in", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(EmailActivity.this, HomeActivity.class));
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
                startActivity(new Intent(EmailActivity.this, CreateActivity.class));
            }
        });
    }
}