/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.avis;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnexion;

/**
 *
 * @author AMEN
 */
public class AvisServices {
          Connection cnx;
    public AvisServices() {
       
        cnx=MaConnexion.getInstance().getCnx();
    }
    
    
    
    
    
    
     public void ajouterAvis(avis a) {

        String sql="insert into avis(id,rating,commentaire,titre) values(?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setInt(1,a.getId());
            ste.setInt(2,a.getRating());
            ste.setString(3,a.getCommentaire());
            ste.setString(4,a.getTitre());


            ste.executeUpdate();
            System.out.println("Avis Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     
     
      public List<avis> afficher() {
        List<avis> lu=new ArrayList<>();
        try {
            
            Statement ste=cnx.createStatement();
            String query="select * from avis";
            ResultSet rs=ste.executeQuery(query);
            while(rs.next()){
                avis a =new avis();
                a.setId(rs.getInt("id"));
 
                a.setRating(rs.getInt("rating"));
                a.setCommentaire(rs.getString("commentaire"));
                a.setTitre(rs.getString("titre"));
                

                lu.add(a);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
        
    }

    

public int supprimerAvis(int id)throws SQLException {
 
            
     int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `avis` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }


    
   
    public void modifierAvis(long rating, avis a) {
        
        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE avis set  id=?,rating=,commentaire=?,titre=? WHERE rating=?");
           
            ste.setInt(1,a.getId());
            ste.setInt(1,a.getRating());
            ste.setString(2,a.getCommentaire());
            ste.setString(3,a.getTitre());
            ste.setLong(4, rating);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
        

    }

    
    
    
    
    
     
   
}







    