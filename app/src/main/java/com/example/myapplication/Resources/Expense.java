package com.example.myapplication.Resources;

import java.util.Date;

public class Expense {

    private String content,catagory,date;
    private double price;


    public Expense(String content, String catagory, double price,String date) {
        this.content = content;
        this.catagory = catagory;
        this.price = price;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
