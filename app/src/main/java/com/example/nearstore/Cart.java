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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nearstore.Adapter.AdapterCart;
import com.example.nearstore.DB.Item;
import com.example.nearstore.DB.ItemViewModel;
import com.example.nearstore.DB.MyDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;


public class Cart extends Fragment {

//    ArrayList<ModelCart> cartitems ;
    //LiveData<List<Item> >cartitems ;
    List<Item >cartitems ;


    RecyclerView cartrecyclerview;
    AdapterCart adapter;
    View noitems;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    private TextView itemTotal, grandTotal;
    private Button confirmOrder;


    ItemViewModel itemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_cart, container, false);

          itemTotal = view.findViewById(R.id.itemtotal);
          grandTotal = view.findViewById(R.id.grandtotal);
            confirmOrder= view.findViewById(R.id.confirmorder);
        confirmOrder.setVisibility(View.INVISIBLE);

            noitems = view.findViewById(R.id.noitems);
          /*  itemViewModel = ViewModelProviders.of(Cart.this).get(ItemViewModel.class);
            itemViewModel.getdata().observe(this, new Observer<List<Item>>() {
                @Override
                public void onChanged(List<Item> items) {

                }
            });
*/
            cartitems = MyDatabase.getInstance(getContext()).dao().getitem();

            if(cartitems.size() != 0){
                confirmOrder.setVisibility(View.VISIBLE);
                noitems.setVisibility(View.INVISIBLE);

            }

       int totalprice = 0;
        for (int i =0; i<cartitems.size();i++){
            totalprice = totalprice + Integer.parseInt(cartitems.get(i).getProductprice())*Integer.parseInt(cartitems.get(i).getProductnumber());
            i++;

        }
        int deliveryfee = 30;

        int grandtotal = totalprice+ deliveryfee;

        itemTotal.setText(String.valueOf(totalprice));
        grandTotal.setText(String.valueOf(grandtotal));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMMM dd,HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String currentDateandTime2 = sdf2.format(new Date());

//        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        OrderDetails orderDetails = new OrderDetails(currentDateandTime,currentDateandTime2,String.valueOf(grandtotal),"","1","0","0","","",cartitems,"0");



confirmOrder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       myRef.child("User").child("HIdVohkuMXQ4HIUHvTwvXXUBlyy1").child("Orders").child(currentDateandTime).setValue(orderDetails);
        Intent intent = new Intent(getActivity(), OrderStatus.class);
        intent.putExtra("OrderId",currentDateandTime);

        startActivity(intent);

        String uniqueID = UUID.randomUUID().toString();
        Toast.makeText(getActivity(),uniqueID,Toast.LENGTH_SHORT).show();

    }
});


       /*     cartitems.observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {



            }
        });

*/

      cartrecyclerview= view.findViewById(R.id.cartitems);
        cartrecyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cartrecyclerview.setNestedScrollingEnabled(false);
        // Connecting object of required Adapter class to
        // the Adapter class itself
       adapter = new AdapterCart(getActivity(),cartitems);
       cartrecyclerview.setNestedScrollingEnabled(false);
        // Connecting Adapter class with the Recycler view
        cartrecyclerview.setAdapter(adapter);





        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);


        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

// Storing the key and its value as the data fetched from edittext
        myEdit.putString("OrderID",currentDateandTime );

        myEdit.commit();








            return view;
    }



}
