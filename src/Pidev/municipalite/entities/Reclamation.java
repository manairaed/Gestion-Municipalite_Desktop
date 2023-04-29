/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.sql.ResultSet;
import java.util.Date;
import javafx.scene.image.ImageView;


public class Reclamation {
    private int id;
    private long  id_user;
    private int tel;
    private String nom;
    private String prenom;
    private String email;
    private String description;
    private Type type_id;
    private String etat;
   private Date date_reclamation; 

    public Reclamation() {
    }

    public Reclamation(int id, long id_user, int tel, String nom, String prenom, String email, String description, Type type_id, String etat, Date date_reclamation) {
        this.id = id;
        this.id_user = id_user;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.type_id = type_id;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation( int tel, String nom, String prenom, String email, String description, Type type_id, String etat, Date date_reclamation) {
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.description = description;
        this.type_id = type_id;
        this.etat = etat;
        this.date_reclamation = date_reclamation;
    }

    public Reclamation(int i, String bagage, String mon_Bagage_est_perdu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }



    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType_id() {
        return  type_id;
    }

    public void setType_id(Type type_id) {
        this.type_id = type_id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", id_user=" + id_user +  ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", description=" + description + ", type_id=" + type_id + ", etat=" + etat + ", date_reclamation=" + date_reclamation + '}';
    }



    public ResultSet executeQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
   
}
