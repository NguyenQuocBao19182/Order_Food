package com.example.philong.order.Object;

import java.io.Serializable;

public class Food implements Serializable {
    private int ID;
    private String foodName;
    private String Price;

    public Food(int ID, String foodName, String price) {
        this.ID = ID;
        this.foodName = foodName;
        Price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
