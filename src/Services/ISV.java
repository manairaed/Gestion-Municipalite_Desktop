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
public interface ISV<V> {
     public List<V> affichervt();
    public void ajoutervt(V a);
       public void modifercat(V a);
    public void supprimervl(int id);
}
