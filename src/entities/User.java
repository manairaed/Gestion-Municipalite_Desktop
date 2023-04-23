/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.Set;


/**
 *
 * @author raedm
 */
public class User {
     private int id;
    private String email;
    private  String password;
    Set<String> roles ;;
    String nomUtil;
    private  String prenomUtil;
    private  int tel;
    private  String adresse;

    public User(int id, String email, String password, Set<String> roles, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User(String email, String password, Set<String> roles, String nomUtil, String prenomUtil, int tel, String adresse) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.tel = tel;
        this.adresse = adresse;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles + ", nomUtil=" + nomUtil + ", prenomUtil=" + prenomUtil + ", tel=" + tel + ", adresse=" + adresse + '}';
    }
    
    
}