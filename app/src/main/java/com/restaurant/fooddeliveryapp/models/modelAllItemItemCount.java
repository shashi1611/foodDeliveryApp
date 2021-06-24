package com.restaurant.fooddeliveryapp.models;

public class modelAllItemItemCount {
    String allItem,itemCount,itemPlate,price;

    public modelAllItemItemCount(String allItem, String itemCount,String itemPlate,String price) {
        this.allItem = allItem;
        this.itemCount = itemCount;
        this.itemPlate=itemPlate;
        this.price=price;
    }

    public String getAllItem() {
        return allItem;
    }

    public void setAllItem(String allItem) {
        this.allItem = allItem;
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
}
