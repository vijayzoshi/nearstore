package com.example.nearstore.ModelClass;

public class ModelShop {


    private String shopname;
    private String shopaddress;
    private String shopid;
    private String shoplang;
    private  String shoplong;
  //  private String deliverytime;
    private int shopimage;



    public String getShoplang() {
        return shoplang;
    }

    public void setShoplang(String shoplang) {
        this.shoplang = shoplang;
    }

    public String getShoplong() {
        return shoplong;
    }

    public void setShoplong(String shoplong) {
        this.shoplong = shoplong;
    }

    public ModelShop(){

    }
    public ModelShop(String shopid, String shopname, String shopaddress, int shopimage,String shoplang, String shoplong){
        this.shopname = shopname;
        this.shopaddress= shopaddress;
        this.shopimage = shopimage;
        this.shopid = shopid;
        this.shoplang = shoplang;
        this.shoplong = shoplong;
    }

    public void setShopname(String shopname){
        this.shopname = shopname;
    }

    public String getShopname(){
        return shopname;
    }

    public void setShopaddress(String shopaddress) {
        this.shopaddress = shopaddress;
    }

    public String getShopaddress(){
        return shopaddress;
    }

    public int getShopimage() {
        return shopimage;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public void setShopimage(int shopimage) {
        this.shopimage = shopimage;
    }
}
