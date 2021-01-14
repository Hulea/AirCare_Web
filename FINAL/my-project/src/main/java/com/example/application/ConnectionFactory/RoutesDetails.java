package com.example.application.ConnectionFactory;

import com.example.application.Model.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoutesDetails {

    List<Route> routes = new ArrayList<>();

    public List<Route> getRoutesDetails(){

        routes.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from routes");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String start = rs.getString("start");
                String destination = rs.getString("destination");
                int hours = rs.getInt("duration_hr");
                int mins = rs.getInt("duration_min");
                int price = rs.getInt("price");
                String route_date = rs.getString("route_date");

                routes.add(new Route(id,start,destination,hours,mins,route_date,price));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return routes;

    }


    public List<Route> getRoutesDetails(String filterText){

        routes.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from routes where start = '"+filterText+"';");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String start = rs.getString("start");
                String destination = rs.getString("destination");
                int hours = rs.getInt("duration_hr");
                int mins = rs.getInt("duration_min");
                int price = rs.getInt("price");
                String route_date = rs.getString("route_date");

                routes.add(new Route(id,start,destination,hours,mins,route_date,price));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return routes;

    }

    public List<Route> getRoutesDetails2(String filterText){

        routes.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from routes where destination = '"+filterText+"';");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                int id = rs.getInt("id");
                String start = rs.getString("start");
                String destination = rs.getString("destination");
                int hours = rs.getInt("duration_hr");
                int mins = rs.getInt("duration_min");
                int price = rs.getInt("price");
                String route_date = rs.getString("route_date");

                routes.add(new Route(start,destination,hours,mins,route_date,price));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return routes;

    }

    public void addRoute(Route aux){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into routes(start,destination,duration_hr,duration_min,route_date,price) values (?,?,?,?,?,?);");
            ps.setString(1,aux.getStart());
            ps.setString(2,aux.getDestination());
            ps.setInt(3,aux.getHours());
            ps.setInt(4,aux.getMinutes());
            ps.setString(5,aux.getRoute_date());
            ps.setInt(6,aux.getPrice());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void editRoute(int id,String st,String dest,int hr,int mn,String rd,int pr){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update routes set start=?,destination=?,duration_hr=?,duration_min=?,route_date=?,price=? where id="+id+";");
            ps.setString(1,st);
            ps.setString(2,dest);
            ps.setInt(3,hr);
            ps.setInt(4,mn);
            ps.setString(5,rd);
            ps.setInt(6,pr);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void deleteRoute(int id){

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("delete from routes where id = "+id+";");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void editRouteStart(String start, int id){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update routes set start = '"+start+"' where id = "+ id +";");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void editRouteDestination(String destination, int id){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update routes set destination = '"+destination+"' where id = "+ id +";");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void editRoutePrice(int price, int id){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update routes set price = "+price+" where id = "+ id +";");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public int price(String start, String desti){

        Connection con = ConnectionFactory.getConnection();
        int price = 0;
        try{
            PreparedStatement ps = con.prepareStatement("select price from routes where start=? and destination = ?;");
            ps.setString(1,start);
            ps.setString(2,desti);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                price = rs.getInt("price");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println(price);
        return price;
    }

}
