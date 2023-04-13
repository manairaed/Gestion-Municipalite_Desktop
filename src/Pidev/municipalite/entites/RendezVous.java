/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.municipalite.entites;

import java.time.LocalDate;

/**
 *
 * @author Farhat
 */
public class RendezVous {
    private int id;
    private String description;
    private LocalDate date_ren;
    private User user;

    public RendezVous(int id) {
        this.id = id;
    }

    public RendezVous(String description, LocalDate date_ren ) {
        this.description = description;
        this.date_ren = date_ren;
    }

    

    public RendezVous(int id, String description, LocalDate  date_ren) {
        this.id = id;
        this.description = description;
        this.date_ren = date_ren;
    }

    public RendezVous() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate  getDate_ren() {
        return date_ren;
    }

    public void setDate_ren(LocalDate date_ren) {
        this.date_ren = date_ren;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", description=" + description + ", date_ren=" + date_ren + '}';
    }
    
    
}
