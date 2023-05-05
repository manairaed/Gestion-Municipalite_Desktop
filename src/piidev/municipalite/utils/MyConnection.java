/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.utils;
import static com.oracle.nio.BufferSecrets.instance;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Amen
 */
public class MyConnection {
     public String url = "jdbc:mysql://localhost:3306/municipalite2";
    public String login="root";
    public String pwd="";
    Connection cnx ;
        private static MyConnection instance;
    
    
     public MyConnection() {
         try {
           cnx = DriverManager.getConnection(url, login, pwd);
           System.out.println("Connextion etablie !");
         } catch (SQLException ex) {
               System.err.println(ex.getMessage());
         }
    
     }
         public static MyConnection getInstance() {
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
     public Connection getCnx() {
        return cnx;
    }
    
}
