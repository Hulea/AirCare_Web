package com.example.application.Model;

public class Route {

    public int getId() {
        return id;
    }

    private int id;
    private String start;
    private String destination;
    private int hours;
    private String route_date;

    public String getRoute_date() {
        return route_date;
    }

    public void setRoute_date(String route_date) {
        this.route_date = route_date;
    }

    public Route(String start, String destination, int hours, int minutes,String route_date, int price) {
        this.start = start;
        this.destination = destination;
        this.hours = hours;
        this.route_date = route_date;
        this.minutes = minutes;
        this.price = price;
    }

    public Route(int id, String start, String destination, int hours, String route_date, int minutes, int price) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.hours = hours;
        this.route_date = route_date;
        this.minutes = minutes;
        this.price = price;
    }

    public Route(int id, String start, String destination, int hours, int minutes, String route_date, int price) {
        this.id = id;
        this.start = start;
        this.destination = destination;
        this.hours = hours;
        this.minutes = minutes;
        this.route_date = route_date;
        this.price = price;
    }

    private int minutes;
    private int price;

    public void setId(int id){
        this.id = id;
    }

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

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Route(String start, String destination, int hours, int minutes, int price) {
        this.start = start;
        this.destination = destination;
        this.hours = hours;
        this.minutes = minutes;
        this.price = price;
    }
}
