/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pidev.municipalite.entites;

/**
 *
 * @author Farhat
 */
public class Document {
    private int id;
    private String name;
    private String image;

    public Document() {
    }

    public Document(int id) {
        this.id = id;
    }

    public Document(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Document(String name) {
        this.name = name;
    }
    

    public Document(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Document{" + "id=" + id + ", name=" + name + ", image=" + image + '}';
    }
    

    
}
