package com.restaurant.fooddeliveryapp.models;

public class modelOrderedItem {
    String name,price,img;
    int count;

    public modelOrderedItem(String name, String price, String img, int count) {
        this.name = name;
        this.price = price;
        this.img = img;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
