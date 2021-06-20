package com.example.nearstore.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearstore.Cart;
import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.MyDatabase;
import com.example.nearstore.ModelClass.ModelCart;
import com.example.nearstore.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder>{

    private Context context;
    private MyDatabase myDatabase;
 //   ArrayList<ModelCart> cartitems ;
    List<Item> cartitems ;
    int price;





    public AdapterCart(Context context, List<Item> cartitems) {
        this.context=context;
        this.cartitems = cartitems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterCart.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_rv, parent, false);
        return new AdapterCart.ViewHolder(view);
    }






    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolder holder, int position) {


     myDatabase = MyDatabase.getInstance(holder.itemView.getContext());



        holder.productnameIV.setText(cartitems.get(position).getProductname());

        holder.productquantityIV.setText(cartitems.get(position).getProductquantity());
   price =Integer.parseInt(cartitems.get(position).getProductprice())*Integer.parseInt(cartitems.get(position).getProductnumber());







        holder.productpriceIV.setText("Rs"+ " " + String.valueOf(price));
      holder.productnumberIV.setText(cartitems.get(position).getProductnumber());

      holder.increamentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   int number =  Integer.parseInt(cartitems.get(position).getProductnumber()) ;
                int  number = Integer.parseInt(myDatabase.dao().get(cartitems.get(position).getProductname())) ;
               if(number<5){

                   number = number+1;
                   String id= cartitems.get(position).getProductname();
                    price =Integer.parseInt(cartitems.get(position).getProductprice())*number;

                   holder.productnumberIV.setText(String.valueOf(number));


                   myDatabase.dao().ProductUpdate(String.valueOf(number), id);
                   notifyDataSetChanged();}
               else{

                    Toast.makeText(view.getContext(),"Max 5 items can be Selected: ",Toast.LENGTH_LONG).show();
                }


            }
        });

        holder.decreamentIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int number =  Integer.parseInt(cartitems.get(position).getProductnumber()) ;
            if(number>1) {
                number = number-1;
                String id= cartitems.get(position).getProductname();
                holder.productnumberIV.setText(String.valueOf(number));
                price =Integer.parseInt(cartitems.get(position).getProductprice())*number;



                myDatabase.dao().ProductUpdate(String.valueOf(number), id);
                notifyDataSetChanged();
            }else {
                String id= cartitems.get(position).getProductname();
                myDatabase.dao().ProductRemove(id);
                notifyDataSetChanged();

}


            }
        });

    }

    @Override
    public int getItemCount() {  return cartitems.size();
    }


  /*  @Override
    public int getItemCount() {
        return cartitems.size();
    }

*/
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productnameIV;
        TextView productquantityIV;
        TextView productpriceIV;
        TextView productnumberIV;
        Button increamentIV, decreamentIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productnameIV = itemView.findViewById(R.id.productnameid);
            productquantityIV= itemView.findViewById(R.id.productquantityid);
            productpriceIV = itemView.findViewById(R.id.productpriceid);
            increamentIV= itemView.findViewById(R.id.increament);
            decreamentIV= itemView.findViewById(R.id.decreament);
            productnumberIV= itemView.findViewById(R.id.productnumber);
        }
    }
}
