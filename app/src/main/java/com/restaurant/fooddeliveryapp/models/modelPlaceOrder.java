package com.restaurant.fooddeliveryapp.models;

import java.util.List;

public class modelPlaceOrder {
    String name,number,street,landmark;
//    List<String>list;
    public modelPlaceOrder(String name, String number, String street, String landmark)
//    ,List<String>list)
    {
        this.name = name;
        this.number = number;
        this.street = street;
        this.landmark = landmark;
//        this.list=list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

//    public List<String> getList() {
//        return list;
//    }

//    public void setList(List<String> list) {
//        this.list = list;
//    }
}
