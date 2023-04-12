/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Ala
 */
import Pidev.municipalite.entites.Vehicule;
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
public class ServiceVt implements ISV<Vehicule> {
private Connection cnx ;
 
public ServiceVt() {
    cnx = Myconnection.getInstance().getCnx();
}

    @Override
    public List<Vehicule> affichervt() {
       List<Vehicule> vehicule = new ArrayList();
   
        try {
        Statement stm =cnx.createStatement();
        String querry ="SELECT * FROM `vehicule`";
     
        ResultSet rs= stm.executeQuery(querry);
       
        while(rs.next()){
            Vehicule a = new Vehicule();
            a.setId(rs.getInt(1));
            a.setCategorie_id(rs.getInt(2));
         a.setUsers_id(rs.getInt(3));
                     a.setMarque(rs.getString(4));
                                 a.setDisponible(rs.getBoolean(5));



           
           
            vehicule.add(a);
        }
       
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
   
    }
   
        return vehicule;

    }

    @Override
    public void ajoutervt(Vehicule a) {
try {
             String querry="INSERT INTO `vehicule`(`categorie_id`, `users_id`, `marque`, `disponible` ) VALUES ('"+a.getCategorie_id()+"','"+a.getUsers_id()+"','"+a.getMarque()+"','"+a.getDisponible()+"')";
            Statement stm =cnx.createStatement();
       
        stm.executeUpdate(querry);
        System.out.println("vehicule ajouter avec sucee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimervl(int id) {
            try {
            String querry = "DELETE FROM `vehicule` WHERE id="+id;
            Statement stm =cnx.createStatement();
            stm.executeUpdate(querry);
            System.out.println("vehicule supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    
    }   

    @Override
    public void modifercat(Vehicule a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

