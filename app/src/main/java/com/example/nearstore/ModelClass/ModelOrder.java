package com.example.nearstore.ModelClass;

public class ModelOrder {

    private String grandtotal,orderDelivered,orderId,orderDateTime;

    public ModelOrder(String grandtotal, String orderDelivered, String orderId, String orderDateTime) {
        this.grandtotal = grandtotal;
        this.orderDelivered = orderDelivered;
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
    }

    public ModelOrder() {
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(String orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
}
