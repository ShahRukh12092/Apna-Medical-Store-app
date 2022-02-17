package com.example.project;

public class Hmedicine {
    private String medicine_id;
    private String name;
    private String quantity;
    private String price;

    public Hmedicine() {

    }

    public Hmedicine(String medicine_id, String name, String quantity, String price) {
        this.medicine_id = medicine_id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
