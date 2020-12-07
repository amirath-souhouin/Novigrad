package com.example.ajare_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RateActivity extends AppCompatActivity {
    private static final String TAG ="" ;
    EditText succ;
    Button submit;
    RatingBar rateBar;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    DatabaseReference databaserate;
    String id;
    Map<String, Object> rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rate = new HashMap<>();
        auth = FirebaseAuth.getInstance();
        succ = findViewById(R.id.ratesucc);
        rateBar = findViewById(R.id.ratingBar);
        submit = findViewById(R.id.submitRate);
        fStore = FirebaseFirestore.getInstance();
        databaserate= FirebaseDatabase.getInstance().getReference("rate");
        databaserate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot  childSnapshot:snapshot.getChildren()){
                    id= childSnapshot.getKey();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        user = auth.getCurrentUser();
        id = auth.getCurrentUser().getUid();
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String s = String.valueOf(rateBar.getRating());
                String name = succ.getText().toString().trim();
                Toast.makeText(getApplicationContext(),s+" Star",Toast.LENGTH_SHORT).show();
                DocumentReference documentReference = fStore.collection("rate").document(id);
                rate.put("Rating",s);
                rate.put("Succursale_Name",name);
                documentReference.set(rate).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG,"Succesfully submitted a service request: "+ id);
                        Toast.makeText(RateActivity.this,"Rate submitted",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


            }


        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaserate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });

    }
}