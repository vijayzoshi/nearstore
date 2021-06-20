package com.example.nearstore;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearstore.Adapter.Adapter;
import com.example.nearstore.Adapter.AdapterOrder;
import com.example.nearstore.ModelClass.ModelOrder;
import com.example.nearstore.ModelClass.model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Orders extends AppCompatActivity {


    private RecyclerView recyclerView;
    AdapterOrder adapterOrder;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

//                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";



        mbase = FirebaseDatabase.getInstance().getReference().child("User").child(currentuser).child("Orders");


        recyclerView = findViewById(R.id.orderrecyclerview);



        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<ModelOrder> options = new FirebaseRecyclerOptions.Builder<ModelOrder>().setQuery(mbase, ModelOrder.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapterOrder = new AdapterOrder(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapterOrder);


    }

    @Override
    public void onStart() {
        super.onStart();
        adapterOrder.startListening();
    }

}
