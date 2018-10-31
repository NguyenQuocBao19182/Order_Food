package com.example.philong.order.Object;

public class FoodOrder  extends Food{
    private int soluong;

    public FoodOrder(int ID, String foodName, String price) {
        super(ID, foodName, price);
    }

    public FoodOrder(int ID, String foodName, String price, int soluong) {
        super(ID, foodName, price);
        this.soluong = soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
