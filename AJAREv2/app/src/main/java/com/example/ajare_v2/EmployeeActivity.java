package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmployeeActivity extends AppCompatActivity{

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    String name;
    Button signOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView userName = (TextView) findViewById(R.id.userName);
        TextView role = (TextView) findViewById(R.id.role);

        name = user.getDisplayName();
        if((name != null) && (name.contains("/"))){
            name = name.split("/")[0];
        } else if(name == null){
            name = user.getEmail().split("@")[0];
        }
        userName.setText(name);
        role.setText("Employee");

        signOut = (Button) findViewById(R.id.btn_signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth authh = FirebaseAuth.getInstance();
                authh.signOut();
                startActivity(new Intent(EmployeeActivity.this, MainActivity.class));
            }
        });
    }
}