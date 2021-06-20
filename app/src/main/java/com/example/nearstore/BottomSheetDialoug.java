package com.example.nearstore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class BottomSheetDialoug extends BottomSheetDialogFragment {


EditText editName;
Button updateName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet_dialoug, container, false);





        editName= view.findViewById(R.id.editText2);
        updateName= view.findViewById(R.id.update);

//                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                editName.setText( dataSnapshot.child("User").child(currentuser).child("Account Name").getValue(String.class));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child("User").child(currentuser).child("Account Name").setValue(editName.getText().toString());

                dismiss();


            }
        });

















        return  view;
    }
}
