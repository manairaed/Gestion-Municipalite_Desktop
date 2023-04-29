/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author AMEN
 */
public class avis {
    private long  id_user;
    private int id;
    private int rating;
    private String titre;
    private String commentaire ;

    public avis() {
    }

    public avis(long id_user, int id, int rating, String titre, String commentaire) {
        this.id_user = id_user;
        this.id = id;
        this.rating = rating;
        this.titre = titre;
        this.commentaire = commentaire;
    }

    public avis(int rating, String titre, String commentaire) {
        this.rating = rating;
        this.titre = titre;
        this.commentaire = commentaire;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "avis{" + "id_user=" + id_user + ", id=" + id + ", rating=" + rating + ", titre=" + titre + ", commentaire=" + commentaire + '}';
    }



    
    
    

}

