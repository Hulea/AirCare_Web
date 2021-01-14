package com.example.application.ConnectionFactory;

import com.example.application.Model.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehiclesDetails {

    List<Vehicle> vehicles = new ArrayList<>();

    public List<Vehicle> getVehicles(){
        vehicles.clear();
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from vehicles order by id;");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int fuelConsumption = rs.getInt("fuel_consumption");
                String damage = rs.getString("damage");
                int capacity = rs.getInt("capacity");
                vehicles.add(new Vehicle(id,name, fuelConsumption, damage, capacity));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> getVehicles(String vehName){
        vehicles.clear();
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from vehicles where name ='"+ vehName +"' order by id;");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int fuelConsumption = rs.getInt("fuel_consumption");
                String damage = rs.getString("damage");
                int capacity = rs.getInt("capacity");
                vehicles.add(new Vehicle(name, fuelConsumption, damage, capacity));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into vehicles(name,fuel_consumption,damage,capacity) values (?,?,?,?)");
            ps.setString(1,vehicle.getName());
            ps.setInt(2,vehicle.getFuelConsumption());
            ps.setString(3,vehicle.getDamage());
            ps.setInt(4,vehicle.getCapacity());
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

//    public void deleteVehicle(int id){
//
//        Connection con = ConnectionFactory.getConnection();
//        try{
//            PreparedStatement ps = con.prepareStatement("delete from vehicles where id = " + id +";");
//            ps.executeUpdate();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//    }

    public void deleteVehicle(int id,String name){

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("delete from vehicles where name = '" + name +"' and id = '"+ id +"';");
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void editVehicle(Vehicle vehicle) {

        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update vehicles set name=?,fuel_consumption=?,damage=?,capacity=? where id = "+vehicle.getId()+";");
            ps.setString(1,vehicle.getName());
            ps.setInt(2,vehicle.getFuelConsumption());
            ps.setString(3,vehicle.getDamage());
            ps.setInt(4,vehicle.getCapacity());
            ps.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
