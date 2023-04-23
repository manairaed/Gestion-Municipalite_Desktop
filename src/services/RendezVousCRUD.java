/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import entities.RendezVous;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Farhat
 */
public class RendezVousCRUD {
    Connection cnx2;

    public RendezVousCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
        
    }
    
    public void ajouterrendezvous(RendezVous r){
        
        try {
            String req = "INSERT INTO `rendez_vous` (`description`, `date_ren`) VALUES ('" + r.getDescription() + "', '" + r.getDate_ren() + "')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendez Vous created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
    public void ajouterrendezvous2(RendezVous r){
        
         try {
            String req2 = "INSERT INTO `rendez_vous` (`description`, `date_ren`) VALUES (?,?)";
            PreparedStatement ps = cnx2.prepareStatement(req2);
            ps.setString(1, r.getDescription());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = dateFormat.format(r.getDate_ren());
        ps.setString(2, dateString);
            ps.executeUpdate();
            System.out.println("rendez vous created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      
    }
   
   

    
  public void modifierrendez(RendezVous r) {
    try {
        String req = "UPDATE rendez_vous SET description=?, date_ren=? WHERE id=?";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setString(1, r.getDescription());
         ps.setTimestamp(2, Timestamp.valueOf(r.getDate_ren()));
        ps.setInt(3, r.getId());
        ps.executeUpdate();
        System.out.println("rendezvous updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    public void supprimerrendez(int id) {
        try {
            String req = "DELETE FROM `rendez_vous` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("rendez vous deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<RendezVous> afficherrendezvous() throws ParseException {
    ObservableList<RendezVous> list =  FXCollections.observableArrayList() ;
    try {
        String req = "Select * from rendez_vous";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            java.util.Date date_ren = rs.getTimestamp("date_ren");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = dateFormat.format(date_ren);

            java.util.Date date = dateFormat.parse(dateString);
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            RendezVous d = new RendezVous(id,description, localDateTime);
            list.add(d);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    public RendezVous getOnerendez(int idrendez) throws SQLException {
        String req = "SELECT * FROM `rendez_vous` where id = ?";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, idrendez);

        ResultSet rs = ps.executeQuery();
        RendezVous o =new RendezVous();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setDescription(rs.getString("description"));
                  String dateString = rs.getString("date_ren");
            LocalDateTime dateTime = LocalDateTime.parse(dateString);
            o.setDate_ren(dateTime);
                   
        }
        ps.close();
        return o;
    }
    public LocalDateTime queryLastMeetingDateTime() {
    LocalDateTime lastMeetingDateTime = null;
    try {
        String req = "SELECT MAX(date_ren) AS lastMeetingDateTime FROM rendez_vous";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            lastMeetingDateTime = rs.getTimestamp("lastMeetingDateTime").toLocalDateTime();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return lastMeetingDateTime;
}

    public RendezVous getOnedoc(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   

        
        
    }
    
    
    
    
    
    
    
    

