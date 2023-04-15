/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Ala
 */
import java.util.List;
/**
 *
 * @author Ala
 */
public interface ISC<T> {
    public List<T> affichercat();
    public void ajoutercat(T a);
    public void modifercat(T a);
    public void supprimercat(int id);
    
}