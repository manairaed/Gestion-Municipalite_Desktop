/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.municipalite.services;
import java.util.List;

/**
 *
 * @author raedm
 */
public interface IService<User> {
   public void ajouter(User u);
    public void supprimer(int id);
    public void modifier(User u);
    public List<User> getAll();
    public User getOneById(int id);
}
