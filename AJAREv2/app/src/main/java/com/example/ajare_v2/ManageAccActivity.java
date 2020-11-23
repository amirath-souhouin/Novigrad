package com.example.ajare_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageAccActivity extends AppCompatActivity {

    String emailToDelete;
    ImageButton back;
    Button deleteAcc;
    Button signOut;
    FirebaseUser user;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageacc);
        auth    = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        EditText editemail = ((EditText) findViewById(R.id.emailToDelete));
        editemail.setText(user.getEmail());
        deleteAcc = (Button) findViewById(R.id.deleteAcc);
        deleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ManageAccActivity.this,R.style.DialogTheme);
                dialog.setTitle("Are you sure you want?");
                dialog.setMessage("Pursuing will permanantly delete this account and all your data will be lost");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isComplete()){
                                    Toast.makeText(ManageAccActivity.this, "Account deleted",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(ManageAccActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    FirebaseAuth authh = FirebaseAuth.getInstance();
                                    authh.signOut();
                                    startActivity(new Intent(ManageAccActivity.this, MainActivity.class));
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                 AlertDialog alertDialog = dialog.create();
                 alertDialog.show();
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
