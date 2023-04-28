/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Ala
 */
public class Categorie {
    private int id;
    private String labelcat;
    

    public Categorie() {
    }

    public Categorie(int id, String labelcat) {
        this.id = id;
        this.labelcat = labelcat;
    }

    public Categorie(String labelcat) {
        this.labelcat = labelcat;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelcat() {
        return labelcat;
    }

    public void setLabelcat(String labelcat) {
        this.labelcat = labelcat;
    }

    @Override
    public String toString() {
        return labelcat;
    }
    
    
    
    
}
