/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev.municipalite.entites;

/**
 *
 * @author Ala
 */
public class Vehicule {
      private int id, categorie_id,users_id ;
    private String marque;
    private boolean disponible ; 

    public Vehicule() {
    }

    public Vehicule(int categorie_id, int users_id, Boolean disponible, String marque) {
        this.categorie_id = categorie_id;
        this.users_id = users_id;
        this.disponible = disponible;
        this.marque = marque;
    }

    public Vehicule(int id, int categorie_id, int users_id, Boolean disponible, String marque) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.users_id = users_id;
        this.disponible = disponible;
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
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
        return "Vehicule{" + "id=" + id + ", categorie_id=" + categorie_id + ", users_id=" + users_id + ", disponible=" + disponible + ", marque=" + marque + '}';
    }

    
    
}
