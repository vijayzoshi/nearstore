package com.example.nearstore.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.MyDatabase;
import com.example.nearstore.ModelClass.ModelCart;
import com.example.nearstore.ModelClass.model;
import com.example.nearstore.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class Adapter extends FirebaseRecyclerAdapter<
        model, Adapter.personsViewholder> {

  //  ArrayList<ModelCart> cartitems= new ArrayList<>();

   private MyDatabase myDatabase;

    List<Item> cartitems ;




    public Adapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull model model) {


        myDatabase = MyDatabase.getInstance(holder.itemView.getContext());
        //holder.updateIV.setVisibility(View.INVISIBLE);

        if(myDatabase.dao().get(model.getProductname())!=null){

            holder.addtocart.setVisibility(View.INVISIBLE);
            holder.updateIV.setVisibility(View.VISIBLE);
            holder.productnumberIV.setText(myDatabase.dao().get(model.getProductname()));
        }else{

            holder.addtocart.setVisibility(View.VISIBLE);
            holder.updateIV.setVisibility(View.INVISIBLE);
           // holder.productnumberIV.setText(myDatabase.dao().get(model.getProductname()));
        }




        holder.productnameIV.setText(model.getProductname());
        holder.productquantityIV.setText(model.getProductquantity());
        holder.productpriceIV.setText("Rs" +" " + model.getProductprice());
    //    holder.productnumberIV.setText(String.valueOf(1));


      //  holder.productimageIV.setImageResource(Integer.parseInt(model.getProductimage()));6.
      //   Picasso.get().load(model.getProductimage()).into(holder.productimageIV);
        Glide.with(holder.productimageIV.getContext()).load(model.getProductimage()).into(holder.productimageIV);



        holder.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Item item = new Item( model.getProductname(), model.getProductquantity(),model.getProductprice(),"1");

              myDatabase.dao().ProductInsertion(item);
                notifyDataSetChanged();


     }
        });


        holder.incIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int number =  Integer.parseInt(model.getProductnumber()) ;
              int  number = Integer.parseInt(myDatabase.dao().get(model.getProductname())) ;
              if(number<5) {
                  number = number + 1;
                  String id = model.getProductname();

                  // Setting updated data in textview
                  holder.productnumberIV.setText(String.valueOf(number));

                  // Writing updated data in room
                  myDatabase.dao().ProductUpdate(String.valueOf(number), id);
                  notifyDataSetChanged();
              }else{
                //  Toast.makeText(holder.g,"Max 5 items can be Selected: ",Toast.LENGTH_LONG).show();
              }
            }
        });
        holder.decIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int number =  Integer.parseInt(model.getProductnumber()) ;

                int  number = Integer.parseInt(myDatabase.dao().get(model.getProductname())) ;
                if(number>1) {
                    number = number - 1;
                    String id = model.getProductname();
                    holder.productnumberIV.setText(String.valueOf(number));
                    myDatabase.dao().ProductUpdate(String.valueOf(number), id);
                    notifyDataSetChanged();
                }else{
                    String id= model.getProductname();
                    myDatabase.dao().ProductRemove(id);
                    notifyDataSetChanged();
                }
            }
        });
    }


    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layouts, parent, false);
        return new Adapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productnameIV;
        TextView productquantityIV;
        TextView productpriceIV;
        ImageView productimageIV;
        TextView productnumberIV;


        Button addtocart;
        View updateIV;
        Button incIV,decIV;

        //  ImageView img;
        public personsViewholder(@NonNull View itemView) {

            super(itemView);

            productnameIV = itemView.findViewById(R.id.productname);
            productquantityIV= itemView.findViewById(R.id.productquantity);
            productpriceIV = itemView.findViewById(R.id.productprice);
            productimageIV = itemView.findViewById(R.id.productimage);
            productnumberIV = itemView.findViewById(R.id.productnumber);
            updateIV = itemView.findViewById(R.id.update);

            incIV = itemView.findViewById(R.id.inc);
            decIV = itemView.findViewById(R.id.dec);

            //  img = itemView.findViewById(R.id.img);


           addtocart= itemView.findViewById(R.id.add);
         //  viewcart= itemView.findViewById(R.id.viewcart);


        }


        @Override
        public void onClick(View view) {

        }
    }



}
