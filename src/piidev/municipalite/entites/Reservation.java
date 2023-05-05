/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.entites;

import java.sql.Date;

/**
 *
 * @author Amen
 */
public class Reservation {
    private int id;
    private Date date_debut ;
    private Date date_fin ;
    private String quantite;
    private String etat ;
    private int outil_id ;
    private int user_id;

    public Reservation(int id, Date date_debut, Date date_fin, String quantite, String etat, int outil_id, int user_id) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.quantite = quantite;
        this.etat = etat;
        this.outil_id = outil_id;
        this.user_id = user_id;
    }

    public Reservation(Date date_debut, Date date_fin, String quantite, String etat, int outil_id, int user_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.quantite = quantite;
        this.etat = etat;
        this.outil_id = outil_id;
        this.user_id = user_id;
    }

    public Reservation(Date date_debut, Date date_fin, String quantite, int outil_id) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.quantite = quantite;
        this.outil_id = outil_id;
    }
    
    

   

    public Reservation() {
    }

    public int getOutil_id() {
        return outil_id;
    }

    public void setOutil_id(int outil_id) {
        this.outil_id = outil_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_deb() {
        return date_debut;
    }

    public void setDate_deb(Date date_deb) {
        this.date_debut = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", date_deb=" + date_debut + ", date_fin=" + date_fin + ", quantite=" + quantite + ", etat=" + etat + ", outil_id=" + outil_id + ", user_id=" + user_id + '}';
    }

   

    

    
    
}
