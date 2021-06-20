package com.example.nearstore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nearstore.Adapter.AdapterShop;
import com.example.nearstore.ModelClass.ModelShop;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class Home extends Fragment  implements InterfaceShop {


    LinearLayout shop1;
    LinearLayout shop2;
    TextView accountname,myaddress;


    ArrayList<ModelShop> shopList;

    RecyclerView shoprecyclerview;
    AdapterShop adapter;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        myaddress = view.findViewById(R.id.textView2);
        accountname= view.findViewById(R.id.textView);


// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("Account Name", "");
        //  int a = sh.getInt("age", 0);

// We can then use the data
        accountname.setText("Hi " +s1);
        // age.setText(String.valueOf(a));

        myaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Data.class);
                startActivity(intent);
            }
        });


       /* shop1 = view.findViewById(R.id.shop1);
        shop2 = view.findViewById(R.id.shop2);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);


        shop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


// Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                myEdit.putString("shop", "Shop1");

                myEdit.commit();



                Intent send = new Intent(getActivity(), Shop1.class);


                startActivity(send);
            }
        });

        shop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


// Creating an Editor object to edit(write to the file)
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
                myEdit.putString("shop", "Shop2");

                myEdit.commit();
                Intent send = new Intent(getActivity(), Shop1.class);
                startActivity(send);
            }
        });


*/


        shopList= new ArrayList<>();

        // ModelCart modelCart = new ModelCart();
        shopList.add(new ModelShop("1001","Dmart","JanakPuri",R.drawable.dmart,"28.621301","77.0712434"));
        shopList.add(new ModelShop("1001","BigBazar","Rajouri Garden",R.drawable.bigbazaar,"28.6417","77.1225"));
        shopList.add(new ModelShop("1001","EasyDay","Dwarka Sector 3",R.drawable.easyday,"28.6078","77.0406"));
        shopList.add(new ModelShop("1001","Rajmandir Hypermarket","Tilak Nagar",R.drawable.rajmand,"28.6389","77.0867"));
        shopList.add(new ModelShop("1001","24 Seven","Karol Bagh",R.drawable.seven,"28.6550","77.1888"));
        shopList.add(new ModelShop("1001","City Store","Saket",R.drawable.sm3,"28.5221","77.2102"));
        shopList.add(new ModelShop("1001","Reliance fresh","Rohini",R.drawable.reliancefresh,"28.7383","77.0822"));
        shopList.add(new ModelShop("1001","Sood Store","Defence Colony",R.drawable.sm4,"28.5734","77.2326"));
        shopList.add(new ModelShop("1001","Spar Supermarket","Dwarka Sector 21",R.drawable.spar,"28.5522","77.0583"));
        shopList.add(new ModelShop("1001","Garg Supermarket","  Model Town",R.drawable.sm1,"28.7095", "77.1888"));
        shopList.add(new ModelShop("1001","Metro Sore","Punjabi Bagh",R.drawable.metro,"28.6620","77.1242"));
        shopList.add(new ModelShop("1001","More Retail","Chanakyapuri",R.drawable.more,"28.5972","77.1904"));
        shopList.add(new ModelShop("1001","BigBazar","Rajendra Nagar",R.drawable.bigbazaar,"28.6372","77.1824"));
        shopList.add(new ModelShop("1001","Spar Supermarket","Vasant Kunj",R.drawable.spar,"28.5293","77.1484"));
        shopList.add(new ModelShop("1001","24 Seven","Greater Kailash",R.drawable.seven,"28.5482","77.2380"));

        shoprecyclerview= view.findViewById(R.id.shopid);

       shoprecyclerview.setNestedScrollingEnabled(false);
        shoprecyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new AdapterShop(shopList,this);
        // Connecting Adapter class with the Recycler view*/
        shoprecyclerview.setAdapter(adapter);



        return view;
    }



    @Override
    public void onItemClick(int position) {


        Intent send = new Intent(getActivity(), Shop1.class);
        send.putExtra("Shop Name",shopList.get(position).getShopname());
        send.putExtra("Shop Address", shopList.get(position).getShopaddress());
        startActivity(send);




        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);


// Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putString("shop", shopList.get(position).getShopid());

        myEdit.commit();


    }


}
