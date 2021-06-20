package com.example.nearstore;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mEditText;
    private TextView mTextView;

    private String number,id;

    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    final DatabaseReference myRef = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);


        mButton = findViewById(R.id.track);
        mEditText= findViewById(R.id.editText);
        mTextView= findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();


        number = getIntent().getStringExtra("number");

        SendVerificationCode();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditText.getText().toString())){
                    Toast.makeText(VerificationActivity.this, "Enter OTP", Toast.LENGTH_LONG).show();
                }else if(mEditText.getText().toString().length()!=6){
                    Toast.makeText(VerificationActivity.this, "Enter Correct OTP", Toast.LENGTH_LONG).show();
                }else{

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, mEditText.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendVerificationCode();
            }
        });

    }

    private void SendVerificationCode(){

        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long l) {
                mTextView.setText(""+l/1000);
                mTextView.setEnabled(false);
            }

            @Override
            public void onFinish() {
                mTextView.setText(" Resend OTP");
                mTextView.setEnabled(true);
            }
        }.start();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                60,                 // Timeout duration

                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                    @Override
                    public void onCodeSent(String id, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(id, forceResendingToken);

                        VerificationActivity.this.id = id;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(VerificationActivity.this, "Failed", Toast.LENGTH_LONG).show();

                    }
                });        // OnVerificationStateChangedCallbacks
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //  Intent intent = new Intent(VerificationActivity.this, DetailsActivity.class);
                            //  startActivity(intent);
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseDatabase database = FirebaseDatabase.getInstance();

                            final DatabaseReference myRef = database.getReference();
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    String data=   dataSnapshot.child("User").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Account Name").getValue(String.class);
                                    if(data == null){


                                        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                        HashMap<String,Object> map1 = new HashMap<>();
                                        map1.put("Phone Number",number);
                                        map1.put("Account Name", null);

                                        map1.put("My Address", "");





                                        myRef.child("User").child(currentuser).updateChildren(map1);





                                        Intent intent = new Intent(VerificationActivity.this, DetailsActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }else{
                                        Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                            //  FirebaseUser user = task.getResult().getUser();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Toast.makeText(VerificationActivity.this, "Verification Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}




