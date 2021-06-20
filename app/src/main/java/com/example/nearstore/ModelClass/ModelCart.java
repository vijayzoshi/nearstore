package com.example.nearstore.ModelClass;

public class
ModelCart {

    private String productname;
    private String productquantity;
    private String productprice;
    private  Integer productnumber;

    public Integer getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(Integer productnumber) {
        this.productnumber = productnumber;
    }

    public ModelCart() {
    }

    public ModelCart(String productname, String productquantity, String productprice, int productnumber) {
        this.productname = productname;
        this.productquantity = productquantity;
        this.productprice = productprice;
        this.productnumber= productnumber;
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
