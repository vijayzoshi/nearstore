package com.example.nearstore;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nearstore.ProductTab.Demo;

public class Shop1 extends AppCompatActivity {


    Button viewcart;
    EditText search;
    CardView card1,card2,card3,card4,card5,card6,card7;
    TextView shopname, shopaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop1);

        //viewcart= findViewById(R.id.viewcart);
        search = findViewById(R.id.searchidd);
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        card7 = findViewById(R.id.card7);
        shopname = findViewById(R.id.shopnameid);
        shopaddress =findViewById(R.id.shopaddressid);

        String shopnamedata= getIntent().getStringExtra("Shop Name");
        String shopaddressdata = getIntent().getStringExtra("Shop Address");
        shopname.setText(shopnamedata);
        shopaddress.setText(shopaddressdata);

        card1.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
        dbInsertion("Aata","Rice","Dal","Oil");
        Intent intent = new Intent(Shop1.this, Demo.class);
        startActivity(intent);
          }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Mil Products","Breakfast Cereals","Bread","Icecream");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Bath","Skin","Hair","Oral");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        }); card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Detergents & Soaps","Dishwash","Fragenence & Repelant","Oral");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        }); card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Noodles & Pasta","Biscuit & Cookies","Chocolates","Snacks & Namkeen");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        }); card6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Tea & Coffee","Softdrinks","Bournvita","Fruit Juice");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        }); card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbInsertion("Diapers","Baby Skin","Baby Hair","Oral");
                Intent intent = new Intent(Shop1.this, Demo.class);
                startActivity(intent);

            }
        });


    /*    viewcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shop1.this, Cart.class);
                startActivity(intent);


            }
        });
*/
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Shop1.this, Search.class);
               startActivity(intent);

            }
        });

    }

  void dbInsertion (String Tab1,String Tab2,String Tab3,String Tab4){

     SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
     // Creating an Editor object to edit(write to the file)
     SharedPreferences.Editor myEdit = sharedPreferences.edit();
     // Storing the key and its value as the data fetched from edittext
     myEdit.putString("Tab 1", Tab1);
     myEdit.putString("Tab 2", Tab2);
     myEdit.putString("Tab 3", Tab3);
     myEdit.putString("Tab 4",Tab4 );

     myEdit.commit();

 }



}

