package com.example.nearstore.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Item.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {


    public abstract DAO dao ();

   private static volatile MyDatabase INSTANCE;
    public static MyDatabase getInstance(Context context) {
        if(INSTANCE==null){
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), MyDatabase.class, "DB_Name").allowMainThreadQueries().build();
                    // allow queries on the main thread.
                    // Don't do this on a real app! See PersistenceBasicSample for an example.


                }
            }
        }
        return INSTANCE;
    }



}



