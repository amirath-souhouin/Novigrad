package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity{

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView userName;
    String name;
    Button manageAcc;
    Button manageServ;
    Button signOut;
    //Storage storage = StorageRoles.admin();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userName = (TextView) findViewById(R.id.userName);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        name = user.getDisplayName();
        userName.setText(name);

        manageAcc = (Button) findViewById(R.id.manageAcc);
        manageAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ManageAccActivity.class));
            }
        });
        manageServ = (Button) findViewById(R.id.manageServ);
        manageServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, ManageServActivity.class));
            }
        });

        signOut = (Button) findViewById(R.id.btn_signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth authh = FirebaseAuth.getInstance();
                authh.signOut();
                startActivity(new Intent(AdminActivity.this, MainActivity.class));
            }
        });
    }
}
