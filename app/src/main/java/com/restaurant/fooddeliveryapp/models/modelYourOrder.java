package com.restaurant.fooddeliveryapp.models;

import java.util.List;

public class modelYourOrder {
    String dateOfOrder,orderCompleteStatus,grandTotal;
    List<String>name;
    List<String>count;
    List<String>price;
    List<String>plate;
    public modelYourOrder(String dateOfOrder, String orderCompleteStatus, String grandTotal, List<String> name, List<String> count, List<String> price, List<String> plate) {
        this.dateOfOrder = dateOfOrder;
        this.orderCompleteStatus = orderCompleteStatus;
        this.grandTotal = grandTotal;
        this.name = name;
        this.count = count;
        this.price = price;
        this.plate = plate;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getCount() {
        return count;
    }

    public void setCount(List<String> count) {
        this.count = count;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    public List<String> getPlate() {
        return plate;
    }

    public void setPlate(List<String> plate) {
        this.plate = plate;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getOrderCompleteStatus() {
        return orderCompleteStatus;
    }

    public void setOrderCompleteStatus(String orderCompleteStatus) {
        this.orderCompleteStatus = orderCompleteStatus;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

//    public List<modelCheckout> getList() {
//        return list;
//    }

//    public void setList(List<modelCheckout> list) {
//        this.list = list;
//    }
}
