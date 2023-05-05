/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.services;

import piidev.municipalite.entites.Reservation;
import piidev.municipalite.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Amen
 */
public class ReservationCrud {
    Connection cnx = MyConnection.getInstance().getCnx();
    
   
    public void ajouter(Reservation p) throws SQLException {
        try {
            String req = "INSERT INTO `Reservation` ( `outil_id` ,`date_debut`, `date_fin`, `quantite`, `etat`) VALUES ('" + p.getOutil_id()+ "','" + p.getDate_deb()+ "','" + p.getDate_fin()+ "','" + p.getQuantite()+ "','" + p.getEtat()+ "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      public ObservableList<Reservation> getAll() {
        ObservableList<Reservation> list =  FXCollections.observableArrayList() ;
        try {
            String req = "Select * from `Reservation`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);    
            while(rs.next()){
                Reservation p = new Reservation(rs.getInt(1),rs.getDate(4), rs.getDate(5), rs.getString(6), rs.getString(7),rs.getInt(2),rs.getInt(3));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
        
    public void supprimer(int id) throws SQLException {
        try {
            String req = "DELETE FROM `Reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void modifier(int id , int id_outil, Date date_deb, Date date_fin ,String quantite ) throws SQLException {
        try {
            String req = "UPDATE `Reservation` SET `outil_id` = '" + id_outil+"',`date_debut` = '" + date_deb+"', `date_fin` = '" + date_fin +  "',`quantite` = '" + quantite +  "' WHERE `Reservation`.`id` = " + id  ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
        public void confirmeEtat(int id  , String etat ) throws SQLException {
        try {
            String req = "UPDATE `Reservation` SET `etat` = '" + etat +  "' WHERE `Reservation`.`id` = " + id  ;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("reservation updated to confirmé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
//       public List<Reservation> trierArticleByDate() {
//        ArrayList<Reservation> listArt = new ArrayList<>();
//        try {
//            String req="select a.id_art, a.titre_art,a.auteur_art,a.description_art,a.date_art,a.likes,a.photo,cat.titre_cat from articles a, categorie cat where (a.id_cat= cat.id_cat)";
//
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            Reservation a =null;
//            while (rs.next()) {
//                //listArt.add(new Article(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6),rs.getInt(7)));
//            a = (new Article(rs.getInt(1),rs.getString(2),rs.getString(3) ,  rs.getString(4), rs.getDate(5),rs.getInt(6),rs.getString(7),rs.getString(8)));
//            listArt.add(a);
//            }
//           
//            Collections.sort(listArt, ReservationComparatorDate);
//            Collections.reverse(listArt);
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return listArt;
//    }
//   

 


}
    
