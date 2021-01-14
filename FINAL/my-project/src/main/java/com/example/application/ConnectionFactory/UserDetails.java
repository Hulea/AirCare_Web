package com.example.application.ConnectionFactory;

import com.example.application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetails {

    List<User> users = new ArrayList<>();


    public List<User> getUsersDetails() {
        users.clear();
        Connection con = ConnectionFactory.getConnection();
        try {

            PreparedStatement ps = con.prepareStatement("select * from users order by id");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("pass");
                String role = rs.getString("acc_type");
                users.add(new User(id,firstName, lastName, email, password, role));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    public int getUserNr() {

        int nr=0;
        users.clear();
        try{
            Connection con = ConnectionFactory.getConnection();

            PreparedStatement ps = con.prepareStatement("select * from users;");

            ResultSet rs = ps.executeQuery();

            while(rs.next()){

                nr++;
                //int id = rs.getInt("id");
                //String firstName = rs.getString("first_name");
                //String lastName = rs.getString("last_name");
                //String email = rs.getString("email");
                //String password = rs.getString("pass");
                //String role = rs.getString("acc_type");
                //users.add(new User(id,firstName, lastName, email, password, role));

            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return nr;

    }

    public List<User> getUsersDetails(String filterText) {

        users.clear();
            try{
                Connection con = ConnectionFactory.getConnection();

                PreparedStatement ps = con.prepareStatement("select * from users where first_name = ? order by id;");
                ps.setString(1,filterText);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){

                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String password = rs.getString("pass");
                    String role = rs.getString("acc_type");
                    users.add(new User(id,firstName, lastName, email, password, role));

                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }

        return users;

    }

    public void addUser(User aux) {
        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into users(id,first_name,last_name,email,pass,acc_type) values (?,?,?,?,?,'user');");
            ps.setString(1,aux.getId());
            ps.setString(2,aux.getFirstName());
            ps.setString(3,aux.getLastName());
            ps.setString(4,aux.getEmail());
            ps.setString(5,aux.getPassword());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void registerUser(User aux) {
        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into users(id,first_name,last_name,email,pass,acc_type) values (?,?,?,?,?,?);");
            ps.setString(1,aux.getId());
            ps.setString(2,aux.getFirstName());
            ps.setString(3,aux.getLastName());
            ps.setString(4,aux.getEmail());
            ps.setString(5,aux.getPassword());
            ps.setString(6,aux.getRole());
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUser(String email){
        Connection con = ConnectionFactory.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("delete from users where email='"+ email +"';");
            ps.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void editUser(String email, String firstname,String lastname, String pass,String role){

        Connection con = ConnectionFactory.getConnection();

        try{
            PreparedStatement ps = con.prepareStatement("update users set first_name=?,last_name=?,pass=?,acc_type=? where email='"+ email +"';");
            ps.setString(1,firstname);
            ps.setString(2,lastname);
            ps.setString(3,pass);
            ps.setString(4,role);
            ps.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
