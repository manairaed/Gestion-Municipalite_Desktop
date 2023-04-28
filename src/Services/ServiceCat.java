/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TreeItem;
import utils.MyDB;

/**
 *
 * @author Ala
 */
public class ServiceCat  implements ISC {
private Connection cnx ;
private static ServiceCat instance ;
 public static ServiceCat getInstance(){
        	if(instance == null) {
			instance = new ServiceCat();
		}
			
		return instance;	
    }
public ServiceCat() {
    cnx = MyDB.getInstance().getcnx();
}

    @Override
    public List<Categorie> affichercat() {
     List<Categorie> categorie = new ArrayList();
   
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `categorie`";
     
        ResultSet rs= stm.executeQuery(querry);
       
        while(rs.next()){
            Categorie a = new Categorie();
            a.setId(rs.getInt(1));
            a.setLabelcat(rs.getString(2));
           
           
            categorie.add(a);
        }
       
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
   
    }
   
        return categorie;

    }
    
     public List<Categorie> afficherCatLable() {
     List<Categorie> categorie = new ArrayList();
   
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT labelcat FROM `categorie`";
     
        ResultSet rs= stm.executeQuery(querry);
       
        while(rs.next()){
            Categorie a = new Categorie();
            a.setLabelcat(rs.getString(1));
           
           
            categorie.add(a);
        }
       
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
   
    }
   
        return categorie;

    }
     
     public Categorie afficheCat(String a) throws SQLException{
         Categorie c = new Categorie();
          try {
         
                         String requete = "SELECT labelcat FROM categorie  WHERE id="+a;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while(rs.next()){
                c.setLabelcat(rs.getString(1));
            }
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
                   
            
    return c;
     }

    @Override
    public void ajoutercat(Categorie a) {
        if(a.getLabelcat()==null && a.getLabelcat().isEmpty())
            System.err.println("cannot add categories, plz fill the label");
        else if(a.getLabelcat().length()<4 && a.getLabelcat().length()>8)
            System.err.println("cannot add categories, plz make label length in 4 to 8");
        else{
            try {
                String querry="INSERT INTO `categorie`(`labelcat`) VALUES ('"+a.getLabelcat()+"')";
                Statement stm =cnx.createStatement();
                stm.executeUpdate(querry);
                System.out.println("categorie ajouter avec sucee");
            }catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void modifercat(Categorie a) {
            try {
            String querry = "UPDATE `categorie` SET `labelcat`='" + a.getLabelcat()+ "' WHERE id=" + a.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("La categorie est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
       
    }
  

    @Override
    public void supprimercat(int id ) {
         try {
            String querry = "DELETE FROM `categorie` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("categorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   
    }

   

 
    

   
   
   
 }

   
   
    
    

