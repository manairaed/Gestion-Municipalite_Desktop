/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.services;

import piidev.municipalite.entites.outils;
import piidev.municipalite.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Amen
 */
public class outilsCRUD {
    Connection cnx = MyConnection.getInstance().getCnx();

   
    public void ajouter(outils p) throws SQLException {
        try {
            String req = "INSERT INTO `outils` (`label_outils`, `quantite`,`image`) VALUES ('" + p.getLabel_outils() + "', '" + p.getQuantite()+ "','" + p.getImage()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("outils created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public ObservableList<outils> getAll() {
        ObservableList<outils> list =  FXCollections.observableArrayList() ;
        try {
            String req = "Select * from `outils`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);    
            while(rs.next()){
                outils p = new outils(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        
    public void supprimer(int id) throws SQLException {
        try {
            String req = "DELETE FROM `outils` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("outil deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void modifier(outils o ) throws SQLException {
        try {
            String req = "UPDATE `outils` SET `label_outils` = '" + o.getLabel_outils() + "', `quantite` = '" + o.getQuantite()+"', `image` = '" + o.getImage() +  "' WHERE `outils`.`id` = " + o.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("outil updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 public outils getOneOffre(int idoutil) throws SQLException {
        String req = "SELECT * FROM `outils` where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idoutil);

        ResultSet rs = ps.executeQuery();
        outils o =new outils();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setLabel_outils(rs.getString("nomOutils"));
                    o.setQuantite(rs.getString("quantite"));
                    o.setImage(rs.getString("image"));
        }
        ps.close();
        return o;
    } 
 
//  public ObservableList<outils> chercheroutils(String nom) throws SQLException { 
//          ObservableList<outils> list =  FXCollections.observableArrayList() ;
//        
//         try { String req = "SELECT * FROM `outils` WHERE `label_outils` = ?";
//          PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setString(1, nom);
//
//        ResultSet rs = ps.executeQuery();
//        outils o =new outils();
//        
//        while (rs.next()) {
//        
//                    o.setId(rs.getInt(1));
//                    o.setLabel_outils(rs.getString("label_outils"));
//                    o.setQuantite(rs.getString("quantite"));
//                    o.setImage(rs.getString("image"));
//
//                list.add(o);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return list;
//    }
    
    public String getoutilsReserve() {
    String nom_outil =null ; 
        try {
            String req = "Select label_outils from `outils` WHERE id=67 ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);    
            while(rs.next()){ 
                 nom_outil =rs.getString(1);

              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return nom_outil;
    }

}

