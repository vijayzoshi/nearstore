package com.example.nearstore.ModelClass;

public class ModelProductList {

 String productname, productnumber, productprice, productquantity;

    public ModelProductList(String productname, String productnumber, String productprice, String productquantity) {
        this.productname = productname;
        this.productnumber = productnumber;
        this.productprice = productprice;
        this.productquantity = productquantity;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(String productquantity) {
        this.productquantity = productquantity;
    }
}
