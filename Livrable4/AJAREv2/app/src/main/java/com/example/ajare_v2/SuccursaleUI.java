package com.example.ajare_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class SuccursaleUI extends AppCompatActivity {
    private static final String TAG = "";
    TextView adress;
    TextView phone;
    FirebaseAuth fAuth;
    FirebaseFirestore fStrore;
    String userID;
    Button changeHour;
    Button addServiceSucc;
    TextView horraire;
    String horaire="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succursale_ui);
        adress = findViewById(R.id.succursaledress);
        phone = findViewById(R.id.numberDisplay);
        changeHour = findViewById(R.id.updateHours);
        horraire = findViewById(R.id.horaireDisplayy);
        fAuth = FirebaseAuth.getInstance();
        fStrore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        final DocumentReference documentReference = fStrore.collection("succursales").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                phone.setText(value.getString("Number : " + "Phone_Number"));
                adress.setText("Novigrad " + value.getString("ZipCode"));
                horraire.setText("Horaire" + "\n"+ value.getString("Horaire"));
            }
        });

        changeHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccursaleUI.this, Schedule.class));
                horaire = Schedule.getActivityInstance().getData();
                documentReference.update("Horaire", horaire);
            }

        });
    }
}

