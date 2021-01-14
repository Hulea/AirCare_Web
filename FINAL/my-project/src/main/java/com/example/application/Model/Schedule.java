package com.example.application.Model;

public class Schedule {


    private String mon_start,tue_start,wen_start,thu_start,fri_start,sat_start,sun_start;
    private String email;
    private String job;
    private int income;
    private int mon,tue,wen,thu,fri,sat,sun,free;

    public String getMon_start() {
        return mon_start;
    }

    public void setMon_start(String mon_start) {
        this.mon_start = mon_start;
    }

    public String getTue_start() {
        return tue_start;
    }

    public void setTue_start(String tue_start) {
        this.tue_start = tue_start;
    }

    public String getWen_start() {
        return wen_start;
    }

    public void setWen_start(String wen_start) {
        this.wen_start = wen_start;
    }

    public String getThu_start() {
        return thu_start;
    }

    public void setThu_start(String thu_start) {
        this.thu_start = thu_start;
    }

    public String getFri_start() {
        return fri_start;
    }

    public void setFri_start(String fri_start) {
        this.fri_start = fri_start;
    }

    public String getSat_start() {
        return sat_start;
    }

    public void setSat_start(String sat_start) {
        this.sat_start = sat_start;
    }

    public String getSun_start() {
        return sun_start;
    }

    public void setSun_start(String sun_start) {
        this.sun_start = sun_start;
    }

    public Schedule(){}

    public Schedule(String email, String job, int income,
                    int mon, String mon_start,
                    int tue, String tue_start,
                    int wen, String wen_start,
                    int thu, String thu_start,
                    int fri, String fri_start,
                    int sat, String sat_start,
                    int sun, String sun_start,
                    int free) {
        this.email = email;
        this.job = job;
        this.income = income;
        this.mon = mon;
        this.mon_start = mon_start;
        this.tue = tue;
        this.tue_start = tue_start;
        this.wen = wen;
        this.wen_start = wen_start;
        this.thu = thu;
        this.thu_start = thu_start;
        this.fri = fri;
        this.fri_start = fri_start;
        this.sat = sat;
        this.sat_start = sat_start;
        this.sun = sun;
        this.sun_start = sun_start;
        this.free = free;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getMon() {
        return mon;
    }

    public Schedule(int mon, int tue, int wen, int thu, int fri, int sat, int sun, int free) {
        this.mon = mon;
        this.tue = tue;
        this.wen = wen;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.free = free;
    }

    public void setMon(int mon) {
        this.mon = mon;
    }

    public int getTue() {
        return tue;
    }

    public void setTue(int tue) {
        this.tue = tue;
    }

    public int getWen() {
        return wen;
    }

    public void setWen(int wen) {
        this.wen = wen;
    }

    public int getThu() {
        return thu;
    }

    public void setThu(int thu) {
        this.thu = thu;
    }

    public int getFri() {
        return fri;
    }

    public void setFri(int fri) {
        this.fri = fri;
    }

    public int getSat() {
        return sat;
    }

    public void setSat(int sat) {
        this.sat = sat;
    }

    public int getSun() {
        return sun;
    }

    public void setSun(int sun) {
        this.sun = sun;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }
}
