package com.example.nearstore;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class loadingDialoug {






        private Activity activity;
        private AlertDialog alertDialog;



        loadingDialoug (Activity myactivity)
        {
            activity= myactivity;
        }

        void startLoadingDialog()
        {
            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
            LayoutInflater inflater = activity.getLayoutInflater();
            builder.setView(inflater.inflate(R.layout.custom_loader,null));
            builder.setCancelable(false);


            alertDialog = builder.create();
            alertDialog.show();
        }


        void dismisDialog()
        {
            alertDialog.dismiss();
        }







}
