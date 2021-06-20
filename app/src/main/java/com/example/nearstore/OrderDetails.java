package com.example.nearstore;

import com.example.nearstore.DB.Item;

import java.util.List;

public class OrderDetails {

    public OrderDetails(String orderId, String orderDateTime,  String grandtotal, String myAddress, String orderReceived, String orderDispatched, String orderDelivered, String riderName, String riderNumber, List<Item> orderlist,String riderAlloted) {
        this.grandtotal = grandtotal;
        this.myAddress = myAddress;
        this.orderReceived = orderReceived;
        this.orderDispatched = orderDispatched;
        this.orderDelivered = orderDelivered;
        this.riderName = riderName;
        this.riderAlloted = riderAlloted;
        this.riderNumber = riderNumber;
        this.orderlist = orderlist;
        this.orderId = orderId;
        this.orderDateTime = orderDateTime;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderTime) {
        this.orderDateTime = orderTime;
    }

    public String getRiderAlloted() {
        return riderAlloted;
    }

    public void setRiderAlloted(String riderAlloted) {
        this.riderAlloted = riderAlloted;
    }

    String grandtotal, orderDateTime, myAddress, orderReceived, orderDispatched, orderDelivered,riderName, riderNumber,riderAlloted, orderId;
    List<Item> orderlist;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getOrderReceived() {
        return orderReceived;
    }

    public void setOrderReceived(String orderReceived) {
        this.orderReceived = orderReceived;
    }

    public String getOrderDispatched() {
        return orderDispatched;
    }

    public void setOrderDispatched(String orderDispatched) {
        this.orderDispatched = orderDispatched;
    }

    public String getOrderDelivered() {
        return orderDelivered;
    }

    public void setOrderDelivered(String orderDelivered) {
        this.orderDelivered = orderDelivered;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderNumber() {
        return riderNumber;
    }

    public void setRiderNumber(String riderNumber) {
        this.riderNumber = riderNumber;
    }

    public List<Item> getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(List<Item> orderlist) {
        this.orderlist = orderlist;
    }
}
