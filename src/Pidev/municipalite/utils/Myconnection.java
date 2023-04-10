/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.municipalite.utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author raedm
 */
public class Myconnection {
    private Connection cnx;
    private static Myconnection instance;
    
    private final String url = "jdbc:mysql://localhost:3306/pidev";
    private final String login = "root";
    private final String pwd = "";
    
    public Myconnection(){
        try {
           cnx= DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static Myconnection getInstance(){
    if(instance == null)
            instance = new Myconnection();
        return instance;
    
    }
    
    public Connection getCnx(){
    return cnx;
    }
    
}
