package com.example.ajare_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.example.ajare_v2.EmailActivity.checkPasswordValidity;
import static com.example.ajare_v2.EmailActivity.emailIsEmpty;

public class SuccursaleConnect extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    EditText emailAddress, passwordd;
    Button login;
    TextView signUp;
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succursale_connect);
        auth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.emailAddress_succursale);
        passwordd = findViewById(R.id.passworddd_succursale);
        signUp = findViewById(R.id.create_btn_succursale);
        login = findViewById(R.id.loginnn_succursale);
        user = FirebaseAuth.getInstance().getCurrentUser();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get user info
                String email = emailAddress.getText().toString();
                String smot = passwordd.getText().toString();

                // validate
                if(emailIsEmpty(email)){
                    emailAddress.setError("Please enter a valid email address.");
                    emailAddress.requestFocus();
                } else if (smot.isEmpty()){
                    passwordd.setError("Please enter a valid password.");
                    passwordd.requestFocus();
                } else if(!checkPasswordValidity(smot)){
                    Toast.makeText(SuccursaleConnect.this, "Enter a password with at least 7 characters", Toast.LENGTH_SHORT).show();
                } else{

                    // once validated, login
                    auth.signInWithEmailAndPassword(email, smot)
                            .addOnCompleteListener(SuccursaleConnect.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(!task.isSuccessful()){
                                        Toast.makeText(SuccursaleConnect.this, "An error occurred...", Toast.LENGTH_SHORT).show();
                                    } else{
                                        Toast.makeText(SuccursaleConnect.this, "You succesfully logged in", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SuccursaleConnect.this, SuccursaleUI.class));
                                    }
                                }
                            });
                }

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccursaleConnect.this, SuccursaleCreate.class));
            }
        });
    }
}