package com.restaurant.fooddeliveryapp.models;

public class modelBiryani {
    String name;
    String image,description,price,fullPlatePrice,halfPlatePrice,halfPrice,fullPrice;

    public modelBiryani(String name, String image, String description, String price,String fullPlatePrice,String halfPlatePrice
    ,String fullPrice,String halfPrice) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.fullPlatePrice=fullPlatePrice;
        this.halfPlatePrice=halfPlatePrice;
        this.fullPrice=fullPrice;
        this.halfPrice=halfPrice;
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

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFullPlatePrice() {
        return fullPlatePrice;
    }

    public void setFullPlatePrice(String fullPlatePrice) {
        this.fullPlatePrice = fullPlatePrice;
    }

    public String getHalfPlatePrice() {
        return halfPlatePrice;
    }

    public String getHalfPrice() {
        return halfPrice;
    }

    public void setHalfPrice(String halfPrice) {
        this.halfPrice = halfPrice;
    }

    public String getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(String fullPrice) {
        this.fullPrice = fullPrice;
    }

    public void setHalfPlatePrice(String halfPlatePrice) {
        this.halfPlatePrice = halfPlatePrice;
    }
}
