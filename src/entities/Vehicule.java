/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import entities.Categorie;

/**
 *
 * @author Ala
 */
public class Vehicule {
      private int id ;
    private String marque;
    private int disponible ; 
    private String labelcat ;
    
    

    public Vehicule() {
    }

    public Vehicule(int categorie_id, int users_id, int disponible, String marque) {
   
        this.disponible = disponible;
        this.marque = marque;
        
    }

    public Vehicule(int id, int categorie_id, int users_id, int disponible, String marque) {
        this.id = id;
        this.disponible = disponible;
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id +  ", disponible=" + disponible + ", marque=" + marque + '}';
    }


    public String getUsers_id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
//
//     public String getCategorieLable() {
//        return categorieLable;
//    }
//
//    public void setCategorieLable(String categorieLable) {
//        this.categorieLable = categorieLable;
//    }

    public void setLabelcat(String labelcat) {
        this.labelcat = labelcat;
    }

//    public void setlabelcat(String string) {
//  this.labelcat = labelcat;    }
//
//
// public String getlabelcat() {
//     this.labelcat = labelcat;
//          ;
//   }
//
//    

    public String getLabelcat() {
        return labelcat;
    }

    
    
}
