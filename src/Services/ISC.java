/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorie;
import java.util.List;
/**
 *
 * @author Ala
 */
public interface ISC {
    public List<Categorie> affichercat();
    public void ajoutercat(Categorie a);
    public void modifercat(Categorie a);
    public void supprimercat(int id);
    
}
