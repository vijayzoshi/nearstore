package com.example.nearstore.ProductTab;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nearstore.Adapter.Adapter;
import com.example.nearstore.ModelClass.model;
import com.example.nearstore.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;


public class Tab2 extends Fragment {
    private RecyclerView recyclerView;
    Adapter adapter; // Create Object of the Adapter class
    DatabaseReference mbase; // Create object of the
    // Firebase Realtime Database

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);

// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("Tab 2", "");
        String shop = sh.getString("shop", "");



        mbase = FirebaseDatabase.getInstance().getReference().child("shop").child(shop).child(s1);

        recyclerView = view.findViewById(R.id.tab2rv);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));


        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>().setQuery(mbase, model.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new Adapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);






























        return view;
    }


    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override public void onStart()
    {
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
