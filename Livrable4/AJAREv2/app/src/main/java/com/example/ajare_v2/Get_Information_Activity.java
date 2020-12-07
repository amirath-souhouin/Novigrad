package com.example.ajare_v2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ajare_v2.Information;
import com.example.ajare_v2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.regex.Pattern;

import javax.annotation.Nullable;

public class Get_Information_Activity extends AppCompatActivity {
    EditText prenom;
    EditText nom;
    EditText phone;
    EditText mail;
    EditText pdf;
    EditText genre;
    Button submit;
    Button upload;
    DatabaseReference databaseinformation;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_);
        prenom=(EditText)findViewById(R.id.prenom);
        nom=(EditText)findViewById(R.id.nom);
        phone=(EditText)findViewById(R.id.phone_1);
        pdf=(EditText)findViewById(R.id.pdf);
        genre=(EditText)findViewById(R.id.genre);
        mail=(EditText)findViewById(R.id.email_1);
        submit=(Button)findViewById(R.id.submit);
        upload=(Button)findViewById(R.id.Uploader);
        databaseinformation= FirebaseDatabase.getInstance().getReference("information");
        databaseReference=FirebaseDatabase.getInstance().getReference("upload pdf ");
        storageReference= FirebaseStorage.getInstance().getReference();

        upload.setEnabled(false);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPdf();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInformation();

            }
        });
    }
    private void selectPdf(){
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDFFILESELECT"),12);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!= null){
            upload.setEnabled(true);
            pdf.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));
        }
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPdf(data.getData());
            }
        });
    }

    private void uploadPdf(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("File is loading ...");
        progressDialog.show();
        StorageReference reference=storageReference.child("Upload Pdf"+System.currentTimeMillis()+".pdf");
        reference.putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri=uriTask.getResult();

                Putpdf putpdf=new Putpdf(pdf.getText().toString(),uri.toString());
                databaseReference.child(databaseReference.push().getKey()).setValue(putpdf);
                Toast.makeText(Get_Information_Activity.this,"File upload avec sucees",Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress=(100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File uploaded..."+(int) progress+"%");
            }
        });
    }


    private void addInformation(){
        String first_name=prenom.getText().toString().trim();
        String laste_name=nom.getText().toString().trim();
        String gmail=mail.getText().toString().trim();
        String number= phone.getText().toString().trim();
        String genr=genre.getText().toString().trim();
        if (TextUtils.isEmpty(first_name) || TextUtils.isEmpty(laste_name)||TextUtils.isEmpty(genr) ||(gmail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(gmail).matches()) || number.matches("[0-9]{10}") )
            Toast.makeText(this, "Veuillez entrer des identifiants valide !!", Toast.LENGTH_LONG).show();
        else {
            databaseinformation.push();
            String id=databaseinformation.push().getKey();
            Information information=new Information(id,first_name,laste_name,gmail,number);
            databaseinformation.child(id).setValue(information);
            Toast.makeText(this,"Information Ajouter avec sucees !",Toast.LENGTH_LONG).show();
        }


    }
}