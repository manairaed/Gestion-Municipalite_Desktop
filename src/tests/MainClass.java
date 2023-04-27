/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import entities.RendezVous;
import java.text.ParseException;
import tools.MyConnection;
import services.DocumentCRUD;
import services.RendezVousCRUD;

/**
 *
 * @author Farhat
 */
public class MainClass {
    public static void main(String[] args) throws ParseException{
        //MyConnection mc =  MyConnection.getInstance(); 
        //MyConnection mc2 =  MyConnection.getInstance(); 
        //System.out.println(mc.hashCode()+" - "+mc2.hashCode());

        
        DocumentCRUD pcd = new DocumentCRUD();
        
        
        System.out.println(pcd.afficherdocument());
        
        
        
    }   
}
