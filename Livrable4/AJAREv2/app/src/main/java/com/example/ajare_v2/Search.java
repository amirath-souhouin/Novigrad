package com.example.ajare_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private static final String TAG ="" ;
    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mSuccursaleList;
    private FirestoreRecyclerAdapter adapter;
    EditText searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchView = findViewById(R.id.searchEdit);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mSuccursaleList = findViewById(R.id.succList);
        //Spinner setup
        Spinner spinner =  findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter_ = ArrayAdapter.createFromResource(this,
                R.array.sort_array, android.R.layout.simple_spinner_item);
        adapter_.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_);
        //Query
        Query query = firebaseFirestore.collection("succursales");
        //RecyclerOptions
        FirestoreRecyclerOptions<Succursale> options = new FirestoreRecyclerOptions.Builder<Succursale>()
                .setQuery(query, Succursale.class)
                .build();
       adapter = new FirestoreRecyclerAdapter <Succursale, SuccursaleViewHolder>(options){

            @NonNull
            @Override
            public SuccursaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_list,parent ,false);
                return new SuccursaleViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(@NonNull SuccursaleViewHolder holder, int position, @NonNull Succursale model) {
                holder.list_serviceType.setText("Service Type : "+ model.getService_Type());
                holder.list_adress.setText("Address:  "+model.getZipCode());
                holder.list_hour.setText("Horaire\n"+model.getHoraire());
            }

        };
       mSuccursaleList.setHasFixedSize(true);
       mSuccursaleList.setLayoutManager(new LinearLayoutManager((this)));
       mSuccursaleList.setAdapter(adapter);
       searchView.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

               Log.d(TAG, "SearchBox has changed to : "+s.toString());
               if(s.toString().isEmpty()){
                   Query query = firebaseFirestore.collection("succursales");
                   //RecyclerOptions
                   FirestoreRecyclerOptions<Succursale> options = new FirestoreRecyclerOptions.Builder<Succursale>()
                           .setQuery(query, Succursale.class)
                           .build();
                   adapter.updateOptions(options);

               }
               else {
                   // User role
                   Spinner spinner =findViewById(R.id.spinner_sort);
                   String text = spinner.getSelectedItem().toString();

                   Query Addressquery = firebaseFirestore.collection("succursales").whereEqualTo(text, s.toString());
                   FirestoreRecyclerOptions<Succursale> opt = new FirestoreRecyclerOptions.Builder<Succursale>()
                           .setQuery(Addressquery, Succursale.class)
                           .build();
                   adapter.updateOptions(opt);
               }

           }
       });

    }


    //View Holder
    private class SuccursaleViewHolder extends RecyclerView.ViewHolder {
        private TextView list_adress;
        private TextView list_serviceType;
        private TextView list_hour;
        public SuccursaleViewHolder(@NonNull View itemView) {
            super(itemView);
            list_adress = itemView.findViewById(R.id.textViewAdd);
            list_serviceType = itemView.findViewById(R.id.textViewS);
            list_hour = itemView.findViewById(R.id.textViewHour);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}