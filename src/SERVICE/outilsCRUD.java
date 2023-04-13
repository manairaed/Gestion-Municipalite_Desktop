/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import edu.esprit.entities.outils;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
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
     public void modifier(int Id, String nomOutils, String quantite ,String image ) throws SQLException {
        try {
            String req = "UPDATE `outils` SET `label_outils` = '" + nomOutils + "', `quantite` = '" + quantite+"', `image` = '" + image +  "' WHERE `outils`.`id` = " + Id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("outil updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 


}

