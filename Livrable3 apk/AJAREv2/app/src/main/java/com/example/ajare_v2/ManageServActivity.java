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
public class ManageServActivity extends AppCompatActivity {

    EditText editTextServiceName;
    EditText editTextDocuments;
    Button buttonAddService;
    Button delete;
    Button update;
    ListView listViewServices;

    List<Service> services;
    DatabaseReference databaseServices;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageserv);
        databaseServices =  FirebaseDatabase.getInstance().getReference("services");
        editTextServiceName = findViewById(R.id.editTextServiceName);
        editTextDocuments = findViewById(R.id.editTextDocument);
        listViewServices = findViewById(R.id.listViewServices);
        buttonAddService = findViewById(R.id.addButton);
        services = new ArrayList<>();
        update = (Button)findViewById(R.id.buttonUpdateProduct);
        delete = (Button)findViewById(R.id.buttonDeleteProduct);
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
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addService();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateService(id,editTextServiceName.getText().toString().trim(),editTextDocuments.getText().toString().trim());
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(id);
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
                ServiceList servicesAdapter = new ServiceList(ManageServActivity.this, services);
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
        final View dialogView = inflater.inflate(R.layout.activity_manageserv, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextServiceName = dialogView.findViewById(R.id.editTextServiceName);
        final EditText editTextDocuments  = dialogView.findViewById(R.id.editTextDocument);
        final Button buttonUpdate = dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextServiceName.getText().toString().trim();
                String documents = editTextDocuments.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateService(serviceId, name, documents);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
            }
        });
    }

    private void updateService(String id, String name, String documents) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        Service service= new Service(id,name,documents);
        dR.setValue(service);
    }

    private void deleteService(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        dR.removeValue();
    }

    private void addService() {
        String name = editTextServiceName.getText().toString().trim();
        String documents = editTextDocuments.getText().toString().trim();
        if(!TextUtils.isEmpty(name)){
            Toast.makeText(this, "NOT IMPLEMENTED YET", Toast.LENGTH_LONG).show();
            String id = databaseServices.push().getKey();
            Service service= new Service(id,name,documents);
            databaseServices.child(id).setValue(service);
            editTextServiceName.setText("");
            editTextDocuments.setText("");
        } else{
            Toast.makeText(this, "PLEASE ENTER A NAME", Toast.LENGTH_LONG).show();
        }

    }
}