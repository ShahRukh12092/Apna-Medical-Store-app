package com.example.project;

public class Horder {
    private String id;
    private String number;
    private String name;
    private String date;
    private String bill;
    private String payment_method;
    private String img;

    public Horder() {
    }

    public Horder(String id, String number, String name, String date, String bill, String payment_method, String img) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.date = date;
        this.bill = bill;
        this.payment_method = payment_method;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
