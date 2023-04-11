
package Pidev.municipalite.services;
import java.util.List;


public interface IService<T> {
   public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    public List<T> getAll();
    public T getOneById(int id);
}
