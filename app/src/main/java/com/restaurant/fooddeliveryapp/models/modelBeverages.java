package com.restaurant.fooddeliveryapp.models;

public class modelBeverages {
    String name;
    String price1l;
    String price2l;
    String price500ml;
    String l1;
    String l2;
    String ml500;
    String image,description,price;

    public modelBeverages(String name, String image, String description, String price
    ,String l1,String l2,String ml500,String price1l,String price2l,String price500ml) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.l1=l1;
        this.l2=l2;
        this.ml500=ml500;
        this.price1l=price1l;
        this.price2l=price2l;
        this.price500ml=price500ml;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public String getPrice1l() {
        return price1l;
    }

    public void setPrice1l(String price1l) {
        this.price1l = price1l;
    }

    public String getPrice2l() {
        return price2l;
    }

    public void setPrice2l(String price2l) {
        this.price2l = price2l;
    }

    public String getPrice500ml() {
        return price500ml;
    }

    public void setPrice500ml(String price500ml) {
        this.price500ml = price500ml;
    }

    public String getL1() {
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getL2() {
        return l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getMl500() {
        return ml500;
    }

    public void setMl500(String ml500) {
        this.ml500 = ml500;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
