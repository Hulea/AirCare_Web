package com.example.application.Model;

public class History {

    private String start;
    private String destination;
    private String date;
    private int price;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public History(String start, String destination, String date, int price) {
        this.start = start;
        this.destination = destination;
        this.date = date;
        this.price = price;
    }



}
