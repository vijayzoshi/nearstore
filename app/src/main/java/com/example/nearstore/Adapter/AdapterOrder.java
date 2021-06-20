package com.example.nearstore.Adapter;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.MyDatabase;
import com.example.nearstore.ModelClass.ModelCart;
import com.example.nearstore.ModelClass.ModelOrder;
import com.example.nearstore.ModelClass.model;
import com.example.nearstore.OrderDetailedDetails;
import com.example.nearstore.OrderDetails;
import com.example.nearstore.OrderDetailsActivity;
import com.example.nearstore.Orders;
import com.example.nearstore.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public  class AdapterOrder extends FirebaseRecyclerAdapter<
        ModelOrder, AdapterOrder.personsViewholder> {


    private Context context;





      public AdapterOrder(@NonNull FirebaseRecyclerOptions<ModelOrder> options) {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull ModelOrder modelOrder) {






        holder.orderidIV.setText("Id :" + " "+modelOrder.getOrderId());
      //  holder.orderdeliveredIV.setText(" 23 Jan");
        holder.orderdatetimeIV.setText(modelOrder.getOrderDateTime());
        holder.grandtotalIV.setText("Rs" + " " +modelOrder.getGrandtotal());




        holder.viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                Intent intent = new Intent(context, OrderDetailedDetails.class);
                intent.putExtra("OrderID2", modelOrder.getOrderId());

                context.startActivity(intent);

            }
        });


    }


    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_rv, parent, false);
        return new AdapterOrder.personsViewholder(view);
    }


    class personsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView grandtotalIV;
        TextView orderdeliveredIV;
        TextView orderidIV;
        TextView orderdatetimeIV;

        Button viewdetails;

        public personsViewholder(@NonNull View itemView) {

            super(itemView);

            context = itemView.getContext();
         grandtotalIV = itemView.findViewById(R.id.grandtotal);
         orderdatetimeIV = itemView.findViewById(R.id.datetime);
         orderdeliveredIV = itemView.findViewById(R.id.deliveredstatus);
         orderidIV = itemView.findViewById(R.id.orderid);


            viewdetails= itemView.findViewById(R.id.viewdetails);


        }


        @Override
        public void onClick(View view) {



        }
    }



}
