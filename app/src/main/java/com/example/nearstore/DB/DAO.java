package com.example.nearstore.DB;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nearstore.ModelClass.ModelCart;

import java.util.List;

@Dao
public interface DAO {


  //  @Query("Select * from Item")
   //LiveData< List<Item> >getitem();

    @Query("Select * from Item")
    List<Item> getitem();

    @Query("Select Product_Number from Item where Product_Name =:i")
    String get(String i);




    @Insert
    void ProductInsertion(Item item);


    @Query("Update Item set Product_Number =:n where Product_Name = :i")
    void ProductUpdate(String n, String i);

  /*  @Query("Delete From User where StudentID =:j ")
    void studentdelete(int j);

*/
  @Query("Delete from Item where Product_Name = :i")
  void ProductRemove(String i);

   // @Query("SELECT * FROM Item WHERE Product_Name = :givenDate" )
    // Item getMessageInfo(String givenDate);






}

