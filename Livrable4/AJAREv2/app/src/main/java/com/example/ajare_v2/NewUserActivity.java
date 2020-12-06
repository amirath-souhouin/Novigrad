package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewUserActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 666;
    Button google;
    Button emailll;
    Button phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


        emailll = findViewById(R.id.emailll);
        emailll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewUserActivity.this, CreateActivity.class));

            }
        });
        google = findViewById(R.id.googleee);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewUserActivity.this, WIPActivity.class));
            }
        });
        phone = findViewById(R.id.phoneee);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewUserActivity.this, WIPActivity.class));
            }
        });

    }
}
