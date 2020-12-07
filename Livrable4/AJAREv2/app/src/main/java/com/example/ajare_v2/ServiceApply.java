package com.example.ajare_v2;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class ServiceApply extends AppCompatActivity {



        EditText editTextServiceName;
        EditText editTextDocuments;
        ListView listViewServices;

        List<Service> services;
        DatabaseReference databaseServices;
        String id;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service_apply);
            databaseServices =  FirebaseDatabase.getInstance().getReference("services");
            listViewServices = findViewById(R.id.listViewServices);
            services = new ArrayList<>();

            //adding an onclicklistener to button
            databaseServices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot  childSnapshot:snapshot.getChildren()){
                        id = childSnapshot.getKey();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Service service = services.get(i);
                    showUpdateDeleteDialog(service.getId(), service.getServiceName());
                    return true;
                }
            });
            listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    startActivity(new Intent(ServiceApply.this,Get_Information_Activity.class
                    ));
                }
            });
        }


        @Override
        protected void onStart() {
            super.onStart();
            //attaching value event listener
            databaseServices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    services.clear();
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Service service = postSnapshot.getValue(Service.class);
                        services.add(service);
                    }
                    ServiceList servicesAdapter = new ServiceList(com.example.ajare_v2.ServiceApply.this, services);
                    listViewServices.setAdapter(servicesAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError){

                }
            });

        }
        private void showUpdateDeleteDialog(final String serviceId, String serviceName) {

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_service_apply, null);
            dialogBuilder.setView(dialogView);
            dialogBuilder.setTitle(serviceName);
            final AlertDialog b = dialogBuilder.create();
            b.show();

        }



}