/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import Pidev.municipalite.entites.RendezVous;
import entities.RendezVous;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
            String req = "UPDATE `rendez_vous` SET `description` = '" + r.getDescription() + "', `date_ren` = '" + r.getDate_ren() + "' WHERE `rendez_vous`.`id` = " + r.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("rendez vous updated !");
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

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(date_ren);

            java.util.Date date = dateFormat.parse(dateString);
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            RendezVous d = new RendezVous(id,description, localDate);
            list.add(d);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}

        
        
    }
    
    
    
    
    
    
    
    

