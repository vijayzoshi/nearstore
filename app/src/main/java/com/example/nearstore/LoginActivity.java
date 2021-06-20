package com.example.nearstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button mButton;
    private EditText mEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButton = findViewById(R.id.track);
        mEditText= findViewById(R.id.editText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mEditText.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Enter Mobile Number", Toast.LENGTH_LONG).show();
                }else if(mEditText.getText().toString().length()!=10){
                    Toast.makeText(LoginActivity.this, "Enter Correct Mobile Number", Toast.LENGTH_LONG).show(); }
                else{




                   Intent intent = new Intent(LoginActivity.this, VerificationActivity.class);
                    intent.putExtra("number", "+91" + mEditText.getText().toString());

                   startActivity(intent);
                }
            }

        });

    }
}
