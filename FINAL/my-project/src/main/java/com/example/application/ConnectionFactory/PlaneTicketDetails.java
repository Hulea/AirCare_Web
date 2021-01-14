package com.example.application.ConnectionFactory;

import com.example.application.Model.PlaneTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//clasa pentru extragerea detaliilor despre biletele de avion din baza de date
public class PlaneTicketDetails {

    List<PlaneTicket> tickets = new ArrayList<>();

    //metoda folosita pentru afisarea istoricului de bilete al unui user
    public List<PlaneTicket> history(String usr){

        tickets.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from plane_ticket where user_email = ?");
            ps.setString(1,usr);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int ticket_id = rs.getInt("ticket_id");
                String user_email = rs.getString("user_email");
                String pilot_email = rs.getString("pilot_email");
                int route_id = rs.getInt("route_id");
                String plane_model = rs.getString("plane_model");
                String ticket_date = rs.getString("ticket_date");

                tickets.add(new PlaneTicket(ticket_id,user_email,pilot_email,route_id,plane_model,ticket_date));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tickets;
    }


    //metoda folosita pentru extragerea datelor cu toate biletele din baza de date
    public List<PlaneTicket> getTicketsDetails(){

        tickets.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from plane_ticket");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int ticket_id = rs.getInt("ticket_id");
                String user_email = rs.getString("user_email");
                String pilot_email = rs.getString("pilot_email");
                int route_id = rs.getInt("route_id");
                String plane_model = rs.getString("plane_model");
                String ticket_date = rs.getString("ticket_date");

                tickets.add(new PlaneTicket(ticket_id,user_email,pilot_email,route_id,plane_model,ticket_date));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tickets;
    }


    //metoda folosita de catre pilot pentru a-si vedea pasagerii
    public List<PlaneTicket> getTicketsDetailsForPilot(String pilott_email){

        tickets.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from plane_ticket where pilot_email = '"+pilott_email+"';");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int ticket_id = rs.getInt("ticket_id");
                String user_email = rs.getString("user_email");
                String pilot_email = rs.getString("pilot_email");
                int route_id = rs.getInt("route_id");
                String plane_model = rs.getString("plane_model");
                String ticket_date = rs.getString("ticket_date");

                tickets.add(new PlaneTicket(ticket_id,user_email,pilot_email,route_id,plane_model,ticket_date));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tickets;
    }

    public List<PlaneTicket> getTicketsDetailsForPilot(String pilott_email, String datte){

        tickets.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from plane_ticket where pilot_email=? and ticket_date=?;");
            ps.setString(1,pilott_email);
            ps.setString(2,datte);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int ticket_id = rs.getInt("ticket_id");
                String user_email = rs.getString("user_email");
                String pilot_email = rs.getString("pilot_email");
                int route_id = rs.getInt("route_id");
                String plane_model = rs.getString("plane_model");
                String ticket_date = rs.getString("ticket_date");

                tickets.add(new PlaneTicket(ticket_id,user_email,pilot_email,route_id,plane_model,ticket_date));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tickets;
    }


    public void buyTicket(String user,String pilot,int route,String plane_model,String ticket_date){

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("insert into plane_ticket(user_email,pilot_email,route_id,plane_model,ticket_date) values (?,?,?,?,?);");
            ps.setString(1,user);
            ps.setString(2,pilot);
            ps.setInt(3,route);
            ps.setString(4,plane_model);
            ps.setString(5,ticket_date);

            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public int returnRouteId(String start, String destination, String date){

        Connection con = ConnectionFactory.getConnection();
        int id = 1;
        try{
            PreparedStatement ps = con.prepareStatement("select id from routes where start = ? and destination = ? and route_date = ?;");
            ps.setString(1,start);
            ps.setString(2,destination);
            ps.setString(3,date);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                id = rs.getInt("id");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }






}
