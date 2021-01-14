package com.example.application.ConnectionFactory;

import com.example.application.Model.IncomeAndSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//Folosita pentru preluarea detaliilor din baza de date despre salariul si programul muncitorilor
public class IncomeAndScheduleDetails {

    List<IncomeAndSchedule> ias = new ArrayList<>();


    //Metoda pentru extragerea tuturor datelor din baza de date despre salar si program al muncitorilor
    public List<IncomeAndSchedule> getIncomeAndScheduleDetails(){
        ias.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from worker_income");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String workType = rs.getString("work_type");
                int monthlyIncome = rs.getInt("monthly");
                int monday = rs.getInt("monday");
                int tuesday = rs.getInt("tuesday");
                int wednesday = rs.getInt("wednesday");
                int thursday = rs.getInt("thursday");
                int friday = rs.getInt("friday");
                int saturday = rs.getInt("saturday");
                int sunday = rs.getInt("sunday");
                int freeDaysLeft = rs.getInt("free_days_left");

                ias.add(new IncomeAndSchedule(firstName,lastName,email,workType,monthlyIncome,
                                monday,tuesday,wednesday,thursday,friday,saturday,sunday,freeDaysLeft));



            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return ias;
    }


    //Metoda folosita pentru preluarea programului si salariului unui muncitor in functie de email
    public List<IncomeAndSchedule> getIncomeAndScheduleDetails(String filterText){
        ias.clear();

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("select * from worker_income where email = '"+ filterText+"';");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String workType = rs.getString("work_type");
                int monthlyIncome = rs.getInt("monthly");
                int monday = rs.getInt("monday");
                int tuesday = rs.getInt("tuesday");
                int wednesday = rs.getInt("wednesday");
                int thursday = rs.getInt("thursday");
                int friday = rs.getInt("friday");
                int saturday = rs.getInt("saturday");
                int sunday = rs.getInt("sunday");
                int freeDaysLeft = rs.getInt("free_days_left");

                ias.add(new IncomeAndSchedule(firstName,lastName,email,workType,
                        monthlyIncome,monday,tuesday,wednesday,thursday,friday,
                        saturday,sunday,freeDaysLeft));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return ias;
    }


    //Metoda folosita de admin pentru modificarea salariului unui muncitor
    public void editIncome(String email,int newIncome){

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps =
        con.prepareStatement("update income_and_schedule set monthly = "+newIncome+" where email ='"+email+"';");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    //Metoda folosita de catre admin pentru modificarea tipului de job al unui muncitor
    public void editWorkType(String email, String newWorkType) {
        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps =
        con.prepareStatement("update income_and_schedule set work_type = '"+newWorkType+"' where email ='"+email+"';");
        ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //Metoda folosita pentru modificarea programului de munca al unui muncitor
    public void editSchedule(String email, int monday, int tuesday,
                             int wednesday, int thursday,
                             int friday, int saturday,
                             int sunday) {

        Connection con = ConnectionFactory.getConnection();

        try{
            String aux = new String();
            aux = "update income_and_schedule set";
            aux = aux + " monday = "+monday+",";
            aux = aux + " tuesday = "+tuesday+",";
            aux = aux + " wednesday = "+wednesday+",";
            aux = aux + " thursday = "+thursday+",";
            aux = aux + " friday = "+friday+",";
            aux = aux + " saturday = "+saturday+",";
            aux = aux + " sunday = "+sunday+" ";
            aux = aux + " where email ='"+email+"';";

            PreparedStatement ps = con.prepareStatement(aux);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    //Metoda folosita de catre admin pentru updatarea sau editarea zilelor libere ale unui muncitor
    public void editFreeDays(String email, String freeDaysLeft) {

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("update income_and_schedule set free_days_left = "+freeDaysLeft+" where email = '"+email+"';");
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    //Metoda folosita de admin pentru a sterge un muncitor
    public void removeWorker(String value) {

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps1 = con.prepareStatement("delete from users where email = '"+ value +"';");
            PreparedStatement ps = con.prepareStatement("delete from income_and_schedule where email = '"+ value +"';");

            ps.executeUpdate();
            ps1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    //metoda folosita de administrator pentru adaugarea unui muncitor
    public void addWorker(
            int id, String first, String last,
            String pass,
            String email, String work_type,
                          int monthly,
                          int monday, int tuesday,
                          int wednesday, int thursday,
                          int friday, int saturday,
                          int sunday,int free){
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps1 = con.prepareStatement("insert into users(id,first_name,last_name,email,pass,acc_type) values (?,?,?,?,?,'worker');");
            ps1.setInt(1,id);
            ps1.setString(2,first);
            ps1.setString(3,last);
            ps1.setString(4,email);
            ps1.setString(5,pass);
            PreparedStatement ps = con.prepareStatement("insert into income_and_schedule(email,work_type,monthly,monday,tuesday,wednesday,thursday,friday,saturday,sunday,free_days_left) values (?,?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, email);
            ps.setString(2,work_type);
            ps.setInt(3,monthly);
            ps.setInt(4,monday);
            ps.setInt(5,tuesday);
            ps.setInt(6,wednesday);
            ps.setInt(7,thursday);
            ps.setInt(8,friday);
            ps.setInt(9,saturday);
            ps.setInt(10,sunday);
            ps.setInt(11,free);

            ps1.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
