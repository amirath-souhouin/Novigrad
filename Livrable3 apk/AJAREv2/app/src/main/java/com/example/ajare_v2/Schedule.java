package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Schedule extends AppCompatActivity {
    static Schedule INSTANCE;
    Button valide;
    boolean checkbox[][] = new boolean[8][7];
    CheckBox[] cbs;
    FirebaseAuth fAuth;
    FirebaseUser user;
    FirebaseFirestore fStrore;
    String userID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);
        fAuth = FirebaseAuth.getInstance();
        fStrore = FirebaseFirestore.getInstance();
        userID = fAuth.getCurrentUser().getUid();
        INSTANCE =this;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .95), (int) (height * .6));
        valide = findViewById(R.id.validate);
        cbs = new CheckBox[56];
        for (int i = 0; i < 56; i++) {
            cbs[i] = new CheckBox(Schedule.this);
            cbs[i].setChecked(false);
        }
        cbs[0] = findViewById(R.id.Lundi910);
        cbs[1] = findViewById(R.id.Lundi1011);
        cbs[2] = findViewById(R.id.Lundi1112);
        cbs[3] = findViewById(R.id.Lundi1213);
        cbs[4] = findViewById(R.id.Lundi1314);
        cbs[5] = findViewById(R.id.Lundi1415);
        cbs[6] = findViewById(R.id.Lundi1516);
        cbs[7] = findViewById(R.id.Lundi1617);
        cbs[8] = findViewById(R.id.Mardi910);
        cbs[9] = findViewById(R.id.Mardi1011);
        cbs[10] = findViewById(R.id.Mardi1112);
        cbs[11] = findViewById(R.id.Mardi1213);
        cbs[13] = findViewById(R.id.Mardi1415);
        cbs[14] = findViewById(R.id.Mardi1516);
        cbs[15] = findViewById(R.id.Mardi1617);
        cbs[16] = findViewById(R.id.Mercredi910);
        cbs[17] = findViewById(R.id.Mercredi1011);
        cbs[18] = findViewById(R.id.Mercredi1112);
        cbs[19] = findViewById(R.id.Mercredi1213);
        cbs[20] = findViewById(R.id.Mercredi1314);
        cbs[21] = findViewById(R.id.Mercredi1415);
        cbs[22] = findViewById(R.id.Mercredi1516);
        cbs[23] = findViewById(R.id.Mercredi1617);
        cbs[24] = findViewById(R.id.Jeudi910);
        cbs[25] = findViewById(R.id.Jeudi1011);
        cbs[26] = findViewById(R.id.Jeudi1112);
        cbs[27] = findViewById(R.id.Jeudi1213);
        cbs[28] = findViewById(R.id.Jeudi1314);
        cbs[29] = findViewById(R.id.Jeudi1415);
        cbs[30] = findViewById(R.id.Jeudi1516);
        cbs[31] = findViewById(R.id.Jeudi1617);
        cbs[32] = findViewById(R.id.Vendredi910);
        cbs[33] = findViewById(R.id.Vendredi1011);
        cbs[34] = findViewById(R.id.Vendredi1112);
        cbs[35] = findViewById(R.id.Vendredi1213);
        cbs[36] = findViewById(R.id.Vendredi1314);
        cbs[37] = findViewById(R.id.Vendredi1415);
        cbs[38] = findViewById(R.id.Vendredi1516);
        cbs[39] = findViewById(R.id.Vendredi1617);

        cbs[40] = findViewById(R.id.Samedi910);
        cbs[41] = findViewById(R.id.Samedi1011);
        cbs[42] = findViewById(R.id.Samedi1112);
        cbs[43] = findViewById(R.id.Samedi1213);
        cbs[44] = findViewById(R.id.Samedi1314);
        cbs[45] = findViewById(R.id.Samedi1415);
        cbs[46] = findViewById(R.id.Samedi1516);
        cbs[47] = findViewById(R.id.Samedi1617);
        cbs[48] = findViewById(R.id.Dimanche910);
        cbs[49] = findViewById(R.id.Dimanche1011);
        cbs[50] = findViewById(R.id.Dimanche1112);
        cbs[51] = findViewById(R.id.Dimanche1213);
        cbs[52] = findViewById(R.id.Dimanche1314);
        cbs[53] = findViewById(R.id.Dimanche1415);
        cbs[54] = findViewById(R.id.Dimanche1516);
        cbs[55] = findViewById(R.id.Dimanche1617);



        final DocumentReference documentReference = fStrore.collection("succursales").document(userID);
       valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int col = 0; col<7;col++){
                    for(int ligne = 0; ligne < 8;ligne++){
                        int finalColonne = col;
                        int finalLigne = ligne;
                        if(cbs[col*8 +ligne].isChecked()){
                            checkbox[finalLigne][finalColonne]= true;
                        }
                        else {
                            checkbox[finalLigne][finalColonne]= false;
                        }

                    }
                }
                String data = readCheckBox();
                Map<String,Object> succursale = new HashMap<>();
                succursale.put("Horaire",data);
                documentReference.set(succursale);
                finish();
            }

        });


    }
    public  static Schedule getActivityInstance(){
        return INSTANCE;
    }
    private String ligneValue(int ligne){
        String hour="";
        if(ligne == 0 ){
            hour = "9-10";
        }
        else if (ligne == 1){
            hour = "10-11";
        }
        else if (ligne == 2){
            hour = "11-12";
        }
        else if (ligne == 3){
            hour = "12-13";
        }
        else if (ligne == 4){
            hour = "13-14";
        }
        else if (ligne == 5){
            hour = "14-15";
        }
        else if (ligne == 6){
            hour = "15-16";
        }
        else if (ligne == 7){
            hour = "16-17";
        }
        return hour;
    }
    private String dayValue(int colonne){
        String day = "";

        if(colonne == 0 ){
            day = "L";
        }
        else if (colonne == 1){
            day = "M";
        }
        else if (colonne== 2){
            day = "Me";
        }
        else if (colonne == 3){
            day = "J";
        }
        else if (colonne == 4){
            day= "V";
        }
        else if (colonne == 5){
            day = "S";
        }
        else if (colonne == 6){
            day = "D";
        }
        return  day;
    }
    public String readCheckBox(){
        String horaire = "";
        String day = "";
        String hour ="";
        for (int colonne = 0; colonne < 7; colonne++) {
            day = dayValue(colonne);
            horaire = horaire + day + " ";
            for (int ligne = 0; ligne < 8; ligne++) {
                if(checkbox[ligne][colonne] == true ){
                    hour = ligneValue(ligne);
                    horaire = horaire + hour + "-";

                }
            }
            horaire = horaire +  "\n" ;
        }
        return horaire;
    }
   public String getData (){
        return readCheckBox();
   }
}

