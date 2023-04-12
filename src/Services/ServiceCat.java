package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ala
 */


import Pidev.municipalite.entites.Categorie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Pidev.municipalite.utils.Myconnection;

/**
 *
 * @author Ala
 */
public class ServiceCat  implements ISC<Categorie> {
private Connection cnx ;
 
public ServiceCat() {
    cnx = Myconnection.getInstance().getCnx();
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

    @Override
    public void ajoutercat(Categorie a) {
          try {
             String querry="INSERT INTO `categorie`(`labelcat`) VALUES ('"+a.getLabelcat()+"')";
            Statement stm =cnx.createStatement();
       
        stm.executeUpdate(querry);
        System.out.println("categorie ajouter avec sucee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }

    @Override
    public void modifercat(Categorie a) {
        try {
            String querry = "UPDATE `categorie` SET `id`='" + a.getId() +"', `labelcat`='" + a.getLabelcat()+ "' WHERE id=" + a.getId();
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("La categorie est modifée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimercat(int id) {
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


