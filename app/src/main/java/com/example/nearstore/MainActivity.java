package com.example.nearstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation_view);
        bottomnav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();




    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem menuItem) {


            Fragment selectedfragment = null;
            switch (menuItem.getItemId()){

                        case R.id.home :
                        selectedfragment = new Home();
                        break;

                        case R.id.cart :
                        selectedfragment = new Cart();
                        break;

                        case R.id.account :
                        selectedfragment = new Account();
                        break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();




            return true;
        }
    };


}
