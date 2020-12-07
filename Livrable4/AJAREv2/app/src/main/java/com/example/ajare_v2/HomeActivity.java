package com.example.ajare_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity{

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DrawerLayout drawerl;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    ActionBarDrawerToggle toggle;
    NavigationView nav;
    Intent info;
    Button connectSuccursale;
    Button signSuccursale;
    String name;
    Button signOut;
    Button updateProf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_nav);
        TextView userName = findViewById(R.id.userName);
        connectSuccursale = findViewById(R.id.succursaleConnect);
        connectSuccursale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SuccursaleConnect.class));
            }
        });
        signSuccursale = findViewById(R.id.succursaleSignIn);
        signSuccursale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SuccursaleCreate.class));
            }
        });
        if(user.getEmail().contains("@novigrad.com")){
            connectSuccursale.setVisibility(View.VISIBLE);
            signSuccursale.setVisibility(View.VISIBLE);
        }
        name = user.getDisplayName();
        if((name != null) && (name.contains("/"))){
            name = name.split("/")[0];
        } else if(name == null){
            name = user.getEmail().split("@")[0];
        }

        TextView role = findViewById(R.id.role);
        if (user.getEmail().contains("@novigrad.com")){
            role.setText("Employee");
        } else {
            role.setText("Client");
        }
        userName.setText(name);

        signOut =  findViewById(R.id.btn_signout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth authh = FirebaseAuth.getInstance();
                authh.signOut();
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
            }
        });
        updateProf =  findViewById(R.id.updateProfile);
        updateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, InformationClient.class));
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerl = findViewById(R.id.drawer);
        nav= findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this,drawerl,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerl.addDrawerListener(toggle);
        toggle.syncState();
        nav.setCheckedItem(R.id.nav_profile);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();
                if(auth.getCurrentUser()==null){
                    info = new Intent(HomeActivity.this,ConnexionActivity.class);
                    startActivity(info);
                    return false;
                }
                if(id ==  R.id.nav_profile) {
                    info = new Intent(HomeActivity.this,WIPActivity.class);
                    startActivity(info);
                    return true;
                }else if (id == R.id.nav_services){
                    info = new Intent(HomeActivity.this, ServiceApply.class);
                    startActivity(info);
                    return true;
                }else if(id == R.id.nav_sign) {
                    FirebaseAuth authh = FirebaseAuth.getInstance();
                    authh.signOut();
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    return true;
                }else if(id==R.id.nav_rates) {
                    startActivity(new Intent(HomeActivity.this, WIPActivity.class));
                    return true;
                }
                else if( R.id.nav_share==id) {
                    startActivity(new Intent(HomeActivity.this, WIPActivity.class));
                    return true;
                }
                else if( R.id.nav_search==id) {
                    startActivity(new Intent(HomeActivity.this, Search.class));
                    return true;
                }
                drawerl.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(drawerl.isDrawerOpen(GravityCompat.START)) {
            drawerl.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu,menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(auth.getCurrentUser()==null){
            info = new Intent(HomeActivity.this,ConnexionActivity.class);
            startActivity(info);
            return false;
        }
        if(id ==  R.id.nav_profile) {
            info = new Intent(HomeActivity.this, WIPActivity.class);
            startActivity(info);
            return true;
        }else if (R.id.nav_services == id){
            info = new Intent(HomeActivity.this, ManageServActivity.class);
            startActivity(info);
            return true;
        }else if(id == R.id.nav_sign) {
            FirebaseAuth authh = FirebaseAuth.getInstance();
            authh.signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            return true;
        }
        else if( id == R.id.nav_search) {
            startActivity(new Intent(HomeActivity.this, WIPActivity.class));
            return true;
        }else if(id==R.id.nav_rates) {
            startActivity(new Intent(HomeActivity.this, WIPActivity.class));
            return true;
        }
        else if( R.id.nav_share==id) {
            startActivity(new Intent(HomeActivity.this, WIPActivity.class));
            return true;
        }

        drawerl.closeDrawer(GravityCompat.START);
        return true;
    }
    public static boolean checkUserRole(String email){
        return email.contains("@novigrad.com");
    }
}
