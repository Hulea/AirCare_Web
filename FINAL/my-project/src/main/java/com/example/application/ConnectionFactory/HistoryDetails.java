package com.example.application.ConnectionFactory;

import com.example.application.Model.History;
import com.example.application.views.RegisterView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//Pentru preluarea detaliilor despre istoricul biletelor din baza de date
public class HistoryDetails {

    List<History> hst = new ArrayList<>();


    //Metoca care preia detaliile despre istoricul biletelor pentru un anumit client
    public List<History> getHistoryDetails(String user_email){

        hst.clear();

        Connection con = ConnectionFactory.getConnection();

        try{

            PreparedStatement ps = con.prepareStatement("select start, destination, ticket_date, price from routes,plane_ticket where id = route_id and user_email = ? order by ticket_date;");
            ps.setString(1,user_email);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String start = rs.getString("start");
                String destination = rs.getString("destination");
                String date = rs.getString("ticket_date");
                int price = rs.getInt("price");

                hst.add(new History(start,destination,date,price));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return hst;
    }


}
