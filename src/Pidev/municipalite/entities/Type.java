/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Date;
import javafx.scene.image.ImageView;


public class Type {
    private int id;
    private String type;


    public Type() {
    }

    public Type(int id, String type) {
        this.id = id;
        this.type = type;

    }

    public Type(String type) {
        this.type = type;

    }

    public Type(int i, String bagage, String mon_Bagage_est_perdu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + '}';
    }
    
   
   
}
