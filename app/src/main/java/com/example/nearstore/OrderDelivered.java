package com.example.nearstore;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderDelivered extends AppCompatActivity {


    TextView help;
    Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_delivered);

        help = findViewById(R.id.help);
        done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDelivered.this, MainActivity.class);
                startActivity(intent);
            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDelivered.this);
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

    }

    @Override
    public void onBackPressed (){
        Toast.makeText(getApplicationContext(),"Order in Progress", Toast.LENGTH_SHORT).show();
    }
}
