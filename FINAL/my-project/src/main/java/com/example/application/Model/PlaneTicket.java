package com.example.application.Model;

public class PlaneTicket {

    private int ticket_id;
    private String user_email;
    private String pilot_email;
    private int route_id;
    private String plane_model;
    private String ticket_date;


    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getPilot_email() {
        return pilot_email;
    }

    public void setPilot_email(String pilot_email) {
        this.pilot_email = pilot_email;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getPlane_model() {
        return plane_model;
    }

    public void setPlane_model(String plane_model) {
        this.plane_model = plane_model;
    }

    public String getTicket_date() {
        return ticket_date;
    }

    public void setTicket_date(String ticket_date) {
        this.ticket_date = ticket_date;
    }



    public PlaneTicket(String user_email, String pilot_email, int route_id, String plane_model, String ticket_date) {
        this.user_email = user_email;
        this.pilot_email = pilot_email;
        this.route_id = route_id;
        this.plane_model = plane_model;
        this.ticket_date = ticket_date;
    }

    public PlaneTicket(int ticket_id, String user_email, String pilot_email, int route_id, String plane_model, String ticket_date) {
        this.ticket_id = ticket_id;
        this.user_email = user_email;
        this.pilot_email = pilot_email;
        this.route_id = route_id;
        this.plane_model = plane_model;
        this.ticket_date = ticket_date;
    }
}
