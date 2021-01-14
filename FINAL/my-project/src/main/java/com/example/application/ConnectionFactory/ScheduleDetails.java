package com.example.application.ConnectionFactory;

import com.example.application.Model.Schedule;
import com.example.application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleDetails {

    Schedule aux = new Schedule();

    public Schedule getScheduleDetails(String email) {

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from income_and_schedule where email = '"+ email +"';");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                aux.setEmail(email);
                aux.setJob(rs.getString("work_type"));
                aux.setIncome(rs.getInt("monthly"));
                aux.setFree(rs.getInt("free_days_left"));
                aux.setMon(rs.getInt("monday"));
                aux.setTue(rs.getInt("tuesday"));
                aux.setWen(rs.getInt("wednesday"));
                aux.setThu(rs.getInt("thursday"));
                aux.setFri(rs.getInt("friday"));
                aux.setSat(rs.getInt("saturday"));
                aux.setSun(rs.getInt("sunday"));
            }

            PreparedStatement ps2 = con.prepareStatement("select * from hrs where email = '"+ email +"';");
            ResultSet rs2 = ps2.executeQuery();

            while(rs2.next()){
                aux.setMon_start(rs2.getString("monday_start"));
                aux.setTue_start(rs2.getString("tuesday_start"));
                aux.setWen_start(rs2.getString("wednesday_start"));
                aux.setThu_start(rs2.getString("thursday_start"));
                aux.setFri_start(rs2.getString("friday_start"));
                aux.setSat_start(rs2.getString("saturday_start"));
                aux.setSun_start(rs2.getString("sunday_start"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return aux;
    }
}
