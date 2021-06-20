package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nearstore.Adapter.AdapterOrder;
import com.example.nearstore.ModelClass.ModelOrder;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderDetailedDetails extends AppCompatActivity {


    private TextView orderID, orderDateTime,grandTotal;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference mbase;

    private RecyclerView recyclerView;
    AdapterOrder adapterOrder;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed_details);
        Intent intent = getIntent();
        String order = intent.getStringExtra("OrderID2");

        orderID  = findViewById(R.id.textView3);
        orderDateTime = findViewById(R.id.textView12);
        grandTotal = findViewById(R.id.grandtotal);

   /*     SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String order = sh.getString("OrderID", "");
        */
        String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";



        mbase= database.getReference().child("User").child(currentuser).child("Orders").child(order).child("orderlist");


        recyclerView = findViewById(R.id.productlistrecyclerview);



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


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";
                orderID.setText( dataSnapshot.child("User").child(currentuser).child("Orders").child(order).child("orderId").getValue(String.class));
                orderDateTime.setText( dataSnapshot.child("User").child(currentuser).child("Orders").child(order).child("orderDateTime").getValue(String.class));
                grandTotal.setText( dataSnapshot.child("User").child(currentuser).child("Orders").child(order).child("grandtotal").getValue(String.class));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapterOrder.startListening();
    }

}
