
package Pidev.municipalite.utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class MyConnection {
    private Connection cnx;
    private static MyConnection instance=null;
    
    private final String url = "jdbc:mysql://localhost:3306/pidev";
    private final String login = "root";
    private final String pwd = "";
    
    private MyConnection(){
        try {
           cnx= DriverManager.getConnection(url, login, pwd);
            System.out.println("Connected To Database");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MyConnection getInstance(){
    if(instance == null){
        instance = new MyConnection();
      
    }
          return instance;
    }
    
    public Connection getCnx(){
    return cnx;
    }
    
}
