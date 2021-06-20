package com.example.nearstore.ProductTab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.nearstore.Pager;
import com.example.nearstore.R;
import com.example.nearstore.Search;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Demo extends AppCompatActivity {


    TabLayout tabLayout;
    EditText search;
    TabItem tabItem1, tabItem2,tabItem3;
    ViewPager viewPager;
 //   PagerAdapter pagerAdapter;
    Pager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

/*
loadingDialoug loadingDialoug = new loadingDialoug(Demo.this);
        loadingDialoug.startLoadingDialog();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialoug.dismisDialog();
            }
        },5000);
*/

        tabLayout = findViewById(R.id.tablayout);
        search= findViewById(R.id.search);
        //tabItem1 = findViewById(R.id.tab1);
      //  tabItem2 = findViewById(R.id.tab2);
      //  tabItem3 = findViewById(R.id.tab3);


// Retrieving the value using its keys the file name
// must be same in both saving and retrieving the data
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

// The value will be default as empty string because for
// the very first time when the app is opened, there is nothing to show
        String s1 = sh.getString("Tab 1", "");
        String s2 = sh.getString("Tab 2", "");
        String s3 = sh.getString("Tab 3", "");
        String s4 = sh.getString("Tab 4", "");


        tabLayout.addTab(tabLayout.newTab().setText(s1));
        tabLayout.addTab(tabLayout.newTab().setText(s2));
        tabLayout.addTab(tabLayout.newTab().setText(s3));
        tabLayout.addTab(tabLayout.newTab().setText(s4));
      //  tabLayout.addTab(tabLayout.newTab().setText(s4));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager= findViewById(R.id.vpager);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Demo.this, Search.class);
                startActivity(intent);

            }
        });



        pager = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pager);



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition() ==0 || tab.getPosition() ==1 || tab.getPosition() ==2|| tab.getPosition() == 3 || tab.getPosition() ==4 ){
                    pager.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}
