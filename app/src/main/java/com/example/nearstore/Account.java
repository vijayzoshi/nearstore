package com.example.nearstore;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.MyDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Account extends Fragment {

   Button logout, address, order;
    TextView accountname, mobileno;
    MyDatabase myDatabase;
    private Activity context;
    Button help;
    TextView editName;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);


        help = view.findViewById(R.id.helpid);
        editName = view.findViewById(R.id.editid);





        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  BottomSheetDialog bottomSheetDialog = new BottomSheetDialog Fragment();
                //   bottomSheetDialog.show(getFragmentManager(),bottomSheetDialog.getTag());

                BottomSheetDialoug bottomSheetDialoug = new BottomSheetDialoug();
                bottomSheetDialoug.show(getFragmentManager(),bottomSheetDialoug.getTag());




            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.support_dialoug,null);

                ImageButton phonecall = mView.findViewById(R.id.phonecallid);
                ImageButton  email = mView.findViewById(R.id.emailid);
                //  LayoutInflater inflater = activity.getLayoutInflater();
                //builder.setView(inflater.inflate(R.layout.support_dialoug,null));
                builder.setView(mView);
                builder.setCancelable(true);
               AlertDialog alertDialog = builder.create();




                phonecall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+91 8377055197"));
                        startActivity(intent);

                        alertDialog.dismiss();
                    }
                });
                email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zoshivj@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "I have an Query");
                        intent.putExtra(Intent.EXTRA_TEXT, "Hi Ridesgo Team");
                        intent.setType("message/rfc822");
                        intent.setPackage("com.google.android.gm");
                        startActivity(intent);

                        alertDialog.dismiss();
                    }
                });



                alertDialog.show();
            }
        });

        logout =  view.findViewById(R.id.logoutid);
        accountname = view.findViewById(R.id.nameid);
        address = view.findViewById(R.id.addressid);
        order = view.findViewById(R.id.orderid);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Orders.class);
                startActivity(intent);


            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);




            }
        });
        mobileno = view.findViewById(R.id.phonenoid);

                logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }
        });

// Read from the database
       myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";
                accountname.setText( dataSnapshot.child("User").child(currentuser).child("Account Name").getValue(String.class));
                mobileno.setText( dataSnapshot.child("User").child(currentuser).child("Phone Number").getValue(String.class));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });







/*
// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("Account Name", "");
        //  int a = sh.getInt("age", 0);

// We can then use the data
        accountname.setText(s1);
        // age.setText(String.valueOf(a));










*/



        return view;
    }
}
