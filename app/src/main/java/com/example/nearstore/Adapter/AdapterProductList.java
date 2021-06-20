package com.example.nearstore.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearstore.ModelClass.ModelOrder;
import com.example.nearstore.ModelClass.ModelProductList;
import com.example.nearstore.OrderDetailedDetails;
import com.example.nearstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;



public  class AdapterProductList extends FirebaseRecyclerAdapter<
        ModelProductList, AdapterProductList.personsViewholder> {


    private Context context;





    public AdapterProductList(@NonNull FirebaseRecyclerOptions<ModelProductList> options) {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull ModelProductList modelProductList) {






        holder.productnameIV.setText("Id :" + " "+modelProductList.getProductname());
        //  holder.orderdeliveredIV.setText(" 23 Jan");
        holder.productnumberIV.setText(modelProductList.getProductnumber());
        holder.productpriceIV.setText("Rs" + " " +modelProductList.getProductprice());
        holder.productquantityIV.setText("Rs" + " " +modelProductList.getProductquantity());






    }


    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchased_items_rv, parent, false);
        return new AdapterProductList.personsViewholder(view);
    }


    class personsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productnameIV;

        TextView productpriceIV;

        TextView productnumberIV;

        TextView productquantityIV;

        public personsViewholder(@NonNull View itemView) {

            super(itemView);

            context = itemView.getContext();
            productnameIV = itemView.findViewById(R.id.productnameid);
            productpriceIV = itemView.findViewById(R.id.productpriceid);
            productnumberIV = itemView.findViewById(R.id.productnumber);
            productquantityIV = itemView.findViewById(R.id.productquantityid);


        }


        @Override
        public void onClick(View view) {



        }
    }



}