package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nearstore.ProductTab.Bottomaddress;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAddress extends AppCompatActivity {

    TextView address;
    ImageView editAddress;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        address = findViewById(R.id.myaddress);
        editAddress = findViewById(R.id.editaddress);


//                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                address.setText( dataSnapshot.child("User").child(currentuser).child("My Address").getValue(String.class));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        editAddress.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                Bottomaddress bottomaddress = new Bottomaddress();
                bottomaddress.show(getSupportFragmentManager(),bottomaddress.getTag());



            }
        });




    }
}
