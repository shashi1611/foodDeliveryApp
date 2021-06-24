package com.restaurant.fooddeliveryapp.models;

public class modelMenu {
    String pic;
    String dish;

    public modelMenu(String pic, String dish) {
        this.pic = pic;
        this.dish = dish;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }
}
