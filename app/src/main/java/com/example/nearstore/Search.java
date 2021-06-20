package com.example.nearstore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nearstore.Adapter.Adapter;
import com.example.nearstore.ModelClass.model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Search extends AppCompatActivity {

EditText search;
ImageButton searchbutton;
    RecyclerView searchrecyclerView;
    Adapter adapter;
    DatabaseReference mbase;


    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Search in Rajmandir Store");
        actionBar.setDisplayHomeAsUpEnabled(true);




        search = findViewById(R.id.search);
        searchbutton = findViewById(R.id.searchbutton);
        searchrecyclerView = findViewById(R.id.searchrecyclerview);
        searchrecyclerView.setLayoutManager(new GridLayoutManager(this,2));


        mbase = FirebaseDatabase.getInstance().getReference().child("shop").child("products");


   /*     search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msearch = search.getText().toString();
                firebaseusersearch(msearch);
            }
        });
*/
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msearch = search.getText().toString();
                firebaseusersearch(msearch);
                Toast.makeText(getApplicationContext(),
                        "Searching...",
                        Toast.LENGTH_SHORT)
                        .show();
            }


        });

    }

    private void firebaseusersearch(String msearch) {

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<model> options = new FirebaseRecyclerOptions.Builder<model>().setQuery(mbase.orderByChild("productname").startAt(msearch).endAt(msearch+"\uf8ff"), model.class).build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new Adapter(options);
        // Connecting Adapter class with the Recycler view*/
        searchrecyclerView.setAdapter(adapter);


        adapter.startListening();

    }

  /*  private void firebaseusersearch(String search) {

        Query firebasequery = mbase.orderByChild("name").startAt(search).endAt(search + "\uf8ff");
        FirebaseRecyclerAdapter<model, productsViewHolder> FRadapter = new FirebaseRecyclerAdapter<model, productsViewHolder>(
                model.class,
                R.layout.custom_grid_layouts,
                productsViewHolder.class,
                mbase

        ) {
            @Override
            protected void onBindViewHolder(@NonNull productsViewHolder holder, int position, @NonNull model model) {
                holder.setdetails(getApplicationContext(),model.getProductname());
            }

            @NonNull
            @Override
            public productsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };
        searchrecyclerView.setAdapter(FRadapter);
    }
*/
/*
    public static class productsViewHolder extends RecyclerView.ViewHolder {

        View mview;
        public productsViewHolder(@NonNull View itemView) {
            super(itemView);
            mview= itemView;
        }

        public  void setdetails(Context ctx,String productname){
            TextView product_name= mview.findViewById(R.id.productname);
            product_name.setText((CharSequence) product_name);
        }
    }
*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


}

