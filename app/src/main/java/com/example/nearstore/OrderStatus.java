package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderStatus extends AppCompatActivity {


    private CheckBox checkBox1,checkBox2,checkBox3;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
  DatabaseReference myRef = database.getReference();
  ImageButton calltoDriver;
  TextView driverName;
  ImageView drivericon;
  TextView help;
  TextView viewDetails;
  TextView orderID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        Intent intent = getIntent();
        String id = intent.getStringExtra("OrderId");
        calltoDriver = findViewById(R.id.calltodriver);
        driverName = findViewById(R.id.drivername);
        drivericon = findViewById(R.id.drivericon);
        help = findViewById(R.id.help);
        viewDetails = findViewById(R.id.viewdetails);
        orderID = findViewById(R.id.textView8);
        orderID.setText(id);

        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderStatus.this,OrderDetailedDetails.class);
                intent.putExtra("OrderID2",orderID.getText());
                startActivity(intent);

            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderStatus.this);
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

        calltoDriver.setVisibility(View.INVISIBLE);

        drivericon.setVisibility(View.INVISIBLE);

        driverName.setVisibility(View.INVISIBLE);

       checkBox1= findViewById(R.id.checkBox1);

        checkBox2=findViewById(R.id.checkBox2);
        checkBox3 =findViewById(R.id.checkBox3);

        checkBox1.setChecked(true);


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String currentuser =  "HIdVohkuMXQ4HIUHvTwvXXUBlyy1";


                String Data1 =     dataSnapshot.child("User").child(currentuser).child("Orders").child(id).child("orderDispatched").getValue(String.class);
                if(Data1.equals("1")){
                    checkBox2.setChecked(true);
                }

                String Data2 =     dataSnapshot.child("User").child(currentuser).child("Orders").child(id).child("orderDelivered").getValue(String.class);

                if (Data2.equals("1")){

                    Intent intent = new Intent(OrderStatus.this, OrderDelivered.class);

                    startActivity(intent);
                }

                String Data3 =     dataSnapshot.child("User").child(currentuser).child("Orders").child(id).child("riderAlloted").getValue(String.class);

                if (Data3.equals("1")){

                    String ridername = dataSnapshot.child("User").child(currentuser).child("Orders").child(id).child("riderName").getValue(String.class);
                            String ridernumber = dataSnapshot.child("User").child(currentuser).child("Orders").child(id).child("riderNumber").getValue(String.class);

                            driverName.setText(ridername);
                            calltoDriver.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + ridernumber));
                                    startActivity(intent);


                                }
                            });
                    calltoDriver.setVisibility(View.VISIBLE);

                    drivericon.setVisibility(View.VISIBLE);

                    driverName.setVisibility(View.VISIBLE);



                }



            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });





    }

    @Override
    public void onBackPressed (){
        Toast.makeText(getApplicationContext(),"BackButton", Toast.LENGTH_SHORT).show();
    }
}
