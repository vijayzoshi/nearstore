package com.example.nearstore.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nearstore.InterfaceShop;
import com.example.nearstore.ModelClass.ModelShop;
import com.example.nearstore.R;

import java.util.ArrayList;

public class AdapterShop extends RecyclerView.Adapter<AdapterShop.ViewHolder> {


    ArrayList<ModelShop> shopList;
    private InterfaceShop interfaceShop;

    public AdapterShop(ArrayList<ModelShop> shopList, InterfaceShop interfaceShop) {
        this.shopList = shopList;
        this.interfaceShop =interfaceShop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoplist_rv, parent, false);
        return new AdapterShop.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.shopNameIV.setText(shopList.get(position).getShopname());

        holder.shopAddressIV.setText(shopList.get(position).getShopaddress());

        holder.shopImageIV.setImageResource(shopList.get(position).getShopimage());

        String shoplat = shopList.get(position).getShoplang();

        String shoplong = shopList.get(position).getShoplong();

        String userlat = "29.9457";
        String userlong = "78.1642";
        String distanceBtw = String.format("%.1f",distance(Double.parseDouble(shoplat),Double.parseDouble(shoplong),Double.parseDouble(userlat),Double.parseDouble(userlong)));

        holder.shopDistanceIV.setText(distanceBtw +" "+"km");


    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView shopNameIV,shopAddressIV, shopDistanceIV;
        ImageView shopImageIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shopNameIV = itemView.findViewById(R.id.shopnameid);
            shopAddressIV = itemView.findViewById(R.id.shopaddressid);
            shopImageIV = itemView.findViewById(R.id.shopimageid);
            shopDistanceIV = itemView.findViewById(R.id.shopdistance);
          //  itemView.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    interfaceShop.onItemClick(getAdapterPosition());
                }
            });


        }


    }

    public static double distance(double lat1, double lon1,double lat2,
                                  double lon2) {

      /*  final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
*/
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
    }


}
