package com.example.nearstore.ProductTab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nearstore.Adapter.Adapter;
import com.example.nearstore.Cart;
import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.MyDatabase;
import com.example.nearstore.ModelClass.model;
import com.example.nearstore.MyAddress;
import com.example.nearstore.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Tab1 extends Fragment {


    private RecyclerView recyclerView;
    Adapter adapter;
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database
    View  gotoCart;
    TextView noofitems;
    TextView viewCart;

    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

             shimmerFrameLayout = view.findViewById(R.id.shimmer_view_container);
            shimmerFrameLayout.startShimmer();
            noofitems = view.findViewById(R.id.noofitems);
            viewCart = view.findViewById(R.id.viewcart);


        gotoCart = view.findViewById(R.id.gotocart);
        List<Item> DB = MyDatabase.getInstance(getContext()).dao().getitem();
        if(DB.size() !=0){

           gotoCart.setVisibility(View.VISIBLE);
            noofitems.setText(String.valueOf(DB.size()) +" "+ "items");




        }else{

            gotoCart.setVisibility(View.GONE);
        }



        viewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent = new Intent(getActivity(),  SecondaryCart.class);
               startActivity(intent);
            }
        });


        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String s1 = sh.getString("Tab 1", "");
        String shop = sh.getString("shop", "");


        mbase = FirebaseDatabase.getInstance().getReference().child("shop").child("Shop1").child("Aata");

        recyclerView = view.findViewById(R.id.tab1rv);
        getrecyclerview();




        return view;
    }

    public void getrecyclerview(){
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>().setQuery(mbase, model.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new Adapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
        shimmerFrameLayout.stopShimmer();

         shimmerFrameLayout.setVisibility(View.GONE);

    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
  /*  @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

*/

}
