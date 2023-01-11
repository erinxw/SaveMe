package com.example.saveme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    private String item, date, id, notes,category;
    private double amount;


    public Data() {
    }

//    public Data(String date, String notes, int amount, String category) {
//        this.category = category;
//        this.date = date;
//        this.notes = notes;
//        this.amount = amount;
//    }
    public Data(double amount, String category, String date, String notes ) {
        this.category = category;
        this.date = date;
        this.notes = notes;
        this.amount = amount;
    }

    public Data(  double amount,String category,String notes) {
        this.notes = notes;
        this.amount = amount;
        this.category = category;
    }

    public Data(String notes, double amount) {
        this.notes = notes;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountTostring(double amount) {
        String amountString = Double.toString(amount);
        return amountString;
    }

    public Date getDateFormat(String date) {

        Date date1 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;


    }
}