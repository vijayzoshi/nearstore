package com.example.nearstore.DB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {


    MyDatabase myDatabase;

    public ItemViewModel(@NonNull Application application) {
        super(application);

        myDatabase = MyDatabase.getInstance(application.getApplicationContext());
    }

   // public LiveData<List<Item>> getdata() {
    //    return myDatabase.dao().getitem();
   // }
}