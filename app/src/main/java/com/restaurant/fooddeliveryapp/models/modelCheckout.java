package com.restaurant.fooddeliveryapp.models;

import org.parceler.Parcel;

@Parcel
public class modelCheckout {

    String itemName,totalPrice,itemImage,itemCount,itemPlate;

    public modelCheckout(String itemName, String totalPrice, String itemImage, String itemCount,String itemPlate) {
        this.itemName = itemName;
        this.totalPrice = totalPrice;
        this.itemImage = itemImage;
        this.itemCount = itemCount;
        this.itemPlate=itemPlate;
    }

    public modelCheckout() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemPlate() {
        return itemPlate;
    }

    public void setItemPlate(String itemPlate) {
        this.itemPlate = itemPlate;
    }

    @Override
    public String toString() {
        return "modelCheckout{" +
                "itemName='" + itemName + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemCount='" + itemCount + '\'' +
                ", itemPlate='" + itemPlate + '\'' +
                '}';
    }
}
