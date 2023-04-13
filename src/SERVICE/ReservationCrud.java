/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVICE;

import edu.esprit.entities.Reservation;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
   

 


}
    
