package com.example.application.Model;

public class Vehicle {

    private int id;
    private String name;
    private int fuelConsumtion;

    public int getId() {
        return id;
    }

    public Vehicle(String name, int fuelConsumtion, String damage, int capacity) {
        this.name = name;
        this.fuelConsumtion = fuelConsumtion;
        this.damage = damage;
        this.capacity = capacity;
    }

    public Vehicle(int id,String name, int fuelConsumtion, String damage, int capacity) {
        this.id = id;
        this.name = name;
        this.fuelConsumtion = fuelConsumtion;
        this.damage = damage;
        this.capacity = capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFuelConsumption() {
        return fuelConsumtion;
    }

    public void setFuelConsumption(int fuelConsumtion) {
        this.fuelConsumtion = fuelConsumtion;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    private String damage;
    private int capacity;

}
