package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Data extends AppCompatActivity {



    EditText stuname, stuclass, homeadd,schooladd;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        stuname= findViewById(R.id.stuname);
        stuclass = findViewById(R.id.grade);
        homeadd = findViewById(R.id.homeadd);
        schooladd = findViewById(R.id.schooladd);

        submit = findViewById(R.id.submit);
      submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("User");

                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                StudentModel studentModel = new StudentModel(stuname.getText().toString(),stuclass.getText().toString(),homeadd.getText().toString(),schooladd.getText().toString());



                myRef.child(currentuser).child("Student Details").setValue(studentModel);
                Intent intent = new Intent(Data.this, MainActivity.class);
                startActivity(intent);
            }
        });





    }
}
