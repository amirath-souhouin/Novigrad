package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageAccActivity extends AppCompatActivity {

    String emailToDelete;
    ImageButton back;
    Button deleteAcc;
    Button signOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageacc);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        emailToDelete = ((EditText) findViewById(R.id.emailToDelete)).getText().toString();

        deleteAcc = (Button) findViewById(R.id.deleteAcc);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageAccActivity.this, WIPActivity.class));
            }
        });
        back = (ImageButton) findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageAccActivity.this, AdminActivity.class));
            }
        });

        signOut = (Button) findViewById(R.id.btn_signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth authh = FirebaseAuth.getInstance();
                authh.signOut();
                startActivity(new Intent(ManageAccActivity.this, MainActivity.class));
            }
        });
    }
}
