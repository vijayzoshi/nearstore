package com.example.nearstore.ProductTab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nearstore.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Bottomaddress extends BottomSheetDialogFragment {


    EditText editAddress;
    Button updateAddress;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_bottomaddress, container, false);




        editAddress= view.findViewById(R.id.editText2);
        updateAddress= view.findViewById(R.id.update);

//                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                editAddress.setText( dataSnapshot.child("User").child(currentuser).child("My Address").getValue(String.class));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        updateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child("User").child(currentuser).child("My Address").setValue(editAddress.getText().toString());

                dismiss();


            }
        });




























        return view;

    }
}
