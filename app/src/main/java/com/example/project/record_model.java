package com.example.project;

import android.provider.ContactsContract;

public class record_model {


    private String date;
    private String bill;
    private String number;
    private String name;
    private String payment_method;

    public record_model() {
    }

    public record_model(String date, String bill, String number, String name, String payment_method) {
        this.date = date;
        this.bill = bill;
        this.number = number;
        this.name = name;
        this.payment_method = payment_method;
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

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
