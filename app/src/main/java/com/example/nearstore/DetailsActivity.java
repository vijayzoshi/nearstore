package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsActivity extends AppCompatActivity {


    EditText name,address;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);



        name = (EditText) findViewById(R.id.stuname);
        address=  findViewById(R.id.addressid);
        mButton= (Button) findViewById(R.id.requestschoolcabid);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                myRef.child("User").child(currentuser).child("Account Name").setValue(name.getText().toString());
                myRef.child("User").child(currentuser).child("My Address").setValue(address.getText().toString());




                name = (EditText) findViewById(R.id.stuname);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("Account Name", name.getText().toString());
                myEdit.putString("My Address", address.getText().toString());

                myEdit.commit();

              //  SharedPreference sharedPreference = new SharedPreference();
                //sharedPreference.setDefaults("Account Name",name.getText().toString(),);
                Intent intent = new Intent(DetailsActivity.this, AddressActivity.class);
                startActivity(intent);


            }
        });


    }
}

