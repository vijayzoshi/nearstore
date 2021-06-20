package com.example.nearstore.DB;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey(autoGenerate = true)
    int ProductID;

    @ColumnInfo(name = "Product_Name")
    String productname;
    @ColumnInfo(name = "Product_Quantity")
    String productquantity;
    @ColumnInfo(name = "Product_Price")
    String productprice;
    @ColumnInfo(name = "Product_Number")
    String productnumber;

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public Item() {
    }

    public Item(String productname, String productquantity, String productprice, String productnumber ) {
        this.productname = productname;
        this.productquantity = productquantity;
        this.productprice = productprice;
        this.productnumber = productnumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }


}
