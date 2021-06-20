package com.example.nearstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddressActivity extends AppCompatActivity {


    private EditText address;
    private Button confirm;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        address = findViewById(R.id.editText2);
        confirm = findViewById(R.id.update);



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.hasChild("My Address")){

                    address.setText("");
                }else{
                    String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";
                    address.setText(snapshot.child("User").child(currentuser).child("My Address").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                myRef.child("User").child(currentuser).child("My Address").setValue(address.getText().toString());



                Intent intent = new Intent(AddressActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
