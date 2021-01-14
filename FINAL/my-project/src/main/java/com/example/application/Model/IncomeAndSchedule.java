package com.example.application.Model;

public class IncomeAndSchedule {

    private String firstName,lastName;
    private String email;
    private String workType;
    private int monthly;
    private int mondayHours;

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    private int tuesdayHours;
    private int wednesdayHours;
    private int thursdayHours;
    private int fridayHours;
    private int saturdayHours;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private int sundayHours;
    private int freeDaysLeft;

    public IncomeAndSchedule(String firstName,String lastName,String email, String workType,
                             int monthly, int mondayHours, int tuesdayHours, int wednesdayHours,
                             int thursdayHours, int fridayHours,
                             int saturdayHours, int sundayHours, int freeDaysLeft) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workType = workType;
        this.monthly = monthly;
        this.mondayHours = mondayHours;
        this.tuesdayHours = tuesdayHours;
        this.wednesdayHours = wednesdayHours;
        this.thursdayHours = thursdayHours;
        this.fridayHours = fridayHours;
        this.saturdayHours = saturdayHours;
        this.sundayHours = sundayHours;
        this.freeDaysLeft = freeDaysLeft;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(int mondayHours) {
        this.mondayHours = mondayHours;
    }

    public int getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(int tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public int getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(int wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public int getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(int thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public int getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(int fridayHours) {
        this.fridayHours = fridayHours;
    }

    public int getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(int saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public int getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(int sundayHours) {
        this.sundayHours = sundayHours;
    }

    public int getFreeDaysLeft() {
        return freeDaysLeft;
    }

    public void setFreeDaysLeft(int freeDaysLeft) {
        this.freeDaysLeft = freeDaysLeft;
    }


}
