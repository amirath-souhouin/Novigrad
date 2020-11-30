package com.example.ajare_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.intellij.lang.annotations.RegExp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.ajare_v2.CreateActivity.emailIsValid;

public class SuccursaleCreate extends AppCompatActivity {
    private static final String TAG ="" ;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    Button schedules;
    EditText courriel;
    EditText address;
    EditText numero;
    EditText password;
    Button createAccount;
    String horaire = "";
    TextView horaireDisplay;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succursale_create);
        auth = FirebaseAuth.getInstance();
        schedules = (Button) findViewById(R.id.schedules);
        courriel = (EditText) findViewById(R.id.emailedit);
        address = (EditText) findViewById(R.id.adressedit);
        numero = (EditText) findViewById(R.id.editTextPhone);
        password = (EditText) findViewById(R.id.passwordedit);
        createAccount = (Button) findViewById(R.id.create);
        horaireDisplay = (TextView) findViewById(R.id.horaire) ;
        fStore = FirebaseFirestore.getInstance();
        schedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SuccursaleCreate.this, Schedule.class));
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String mail = courriel.getText().toString();
                final String zipCode = address.getText().toString();
                final String number = numero.getText().toString();
                String pass = password.getText().toString();
                if(emailIsValid(mail)){
                    courriel.setError("Please enter a valid email address.");
                    courriel.requestFocus();
                }
                else if(!addressIsValid(zipCode)) {
                    Toast.makeText(SuccursaleCreate.this, "The address must be in the form A1A 1A1 or A1A1A1", Toast.LENGTH_SHORT).show();
                } else if (!numberIsValid(number)) {
                    Toast.makeText(SuccursaleCreate.this, "The phone number must be in the form XXX-XXX-XXXX", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(mail, pass)
                            .addOnCompleteListener(SuccursaleCreate.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        user = auth.getCurrentUser();
                                        id = auth.getCurrentUser().getUid();
                                        DocumentReference documentReference =  fStore.collection("succursales").document(id);
                                        Map<String,Object> succursale = new HashMap<>();
                                        succursale.put("ZipCode",zipCode);
                                        succursale.put("Email",mail);
                                        succursale.put("Phone_Number", number);
                                        succursale.put("Horaire",horaire );
                                        documentReference.set(succursale).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               Log.d(TAG,"User profile created for : "+ id);
                                            }
                                        });
                                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                .setDisplayName(address.getText().toString()) // change to name
                                                .build();
                                        user.updateProfile(profileUpdates);
                                        startActivity(new Intent(SuccursaleCreate.this, SuccursaleUI.class));
                                        Toast.makeText(SuccursaleCreate.this, "Account created", Toast.LENGTH_SHORT).show();


                                    }
                                    else {
                                        Toast.makeText(SuccursaleCreate.this, "An error occured", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    public static boolean numberIsValid(String number) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean firstLetterPostalCode(String postal){
        for(char c :"DFIOQUWZ".toCharArray()){
            if(postal.charAt(0)== c){
                return false;
            }
        }
        return true;
    }
    public static boolean addressIsValid(String postalCode) {
                //DFIOQUWZ
            String Regex = "^(?<full>(?<part1>[ABCEGHJKLMNPRSTVXY]{1}\\d{1}[A-Z]{1})(?:[ ](?=\\d))?(?<part2>\\d{1}[A-Z]{1}\\d{1}))$";
            Pattern pc = Pattern.compile(Regex);
            Matcher matcher = pc.matcher(postalCode);
            boolean result = matcher.find();
            return result;
    }


}