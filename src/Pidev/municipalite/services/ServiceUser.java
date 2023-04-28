
package Pidev.municipalite.services;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.utils.MyConnection;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;


public class ServiceUser implements IService<User>{
    
Connection cnx = MyConnection.getInstance().getCnx();

    public User Signin (String email , String password){
       try{
           String req5 = "SELECT * FROM user WHERE email = ?";
          PreparedStatement pr5 = cnx.prepareStatement(req5);
          pr5.setString(1, email);
          ResultSet rs = pr5.executeQuery();
          if (rs.next()){
              String storedPassword = rs.getString("password");
              String mot = storedPassword.replaceFirst("^\\$2y\\$", "\\$2a\\$");
              
              if (BCrypt.checkpw(password, mot)){
                  User u = new User();
                  u.setId(rs.getInt("id"));
                  
                  u.setEmail(rs.getString("email"));
                  System.out.println(u);
                  try{
                      FileWriter writer = new FileWriter("session.txt",false);
                      
                      writer.write(String.valueOf(u.getId()));
                      writer.flush();
                      writer.close();
                      return u;
                  }catch(IOException ex ){
                     
                  }
              }else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText("Mot de passe incorrecte");
            alert.showAndWait();
              }
          }else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText("Utilisateur existe pas !");
            alert.showAndWait();
          }
       }catch(SQLException ex){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur login");
            alert.setHeaderText(ex.getMessage());
            alert.showAndWait();
       }
        
        return null;
    }

    @Override
    public void ajouter(User u) {
        User p =u;
        
                try {
                    String req ="SELECT * FROM user  where email = ?";
                    PreparedStatement pr = cnx.prepareStatement(req);
                    pr.setString(1, p.getEmail());
                    ResultSet rs = pr.executeQuery();
                    if(rs.next()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Utilisateur existe déja !");
            alert.showAndWait();
                   }}catch (SQLException ex ){
                    System.out.println(ex.getMessage());
                }
                    
                    
                    String email = p.getEmail();
                    String password = p.getPassword();
                    String hashedPassword =BCrypt.hashpw(password, BCrypt.gensalt(12));
                    String role = p.getRoles();
                    String nom = p.getNomUtil();
                    String prenom = p.getPrenomUtil();
                    int tel = p.getTel();
                    String adresse = p.getAdresse();
                    
                    try{
                       String req1 ="INSERT INTO user( email, roles, password, is_verified, nom_util, prenom_util, tel, adresse) values(?,?,?,?,?,?,?,?) ";
                       PreparedStatement pr1 = cnx.prepareStatement(req1);
                       pr1.setString(1, email);
                       pr1.setString(2, role);
                       pr1.setString(3, hashedPassword);
                       pr1.setBoolean(4, true);
                       pr1.setString(5, nom);
                       pr1.setString(6, prenom);
                       pr1.setInt(7, tel);
                       pr1.setString(8, adresse);
                       pr1.executeUpdate();
                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Bienvenue");
            alert.setHeaderText("Votre compte est créer");
            alert.showAndWait();
                    }catch(SQLException ex){
                        System.out.println(ex.getMessage());
                    }
                    
                }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `user` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Utilisateur deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User u) {
       try{
        String updatereq="UPDATE user SET email=?,nom_util=?,prenom_util=?,tel=?,adresse=?  WHERE id=?";
         PreparedStatement pr2 = cnx.prepareStatement(updatereq);
         pr2.setString(1, u.getEmail());
           pr2.setString(2, u.getNomUtil());
            pr2.setString(3, u.getPrenomUtil());
             pr2.setInt(4, u.getTel());
              pr2.setString(5, u.getAdresse());
              pr2.setInt(6, u.getId());
             pr2.executeUpdate();
             System.out.println("Utilisateur modifier avec succé");
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
       }
    }

    @Override
    public List<User> getAll() {
       List<User> list = new ArrayList<>();
        try {
            String req = "Select * from user ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
               User u = new User();
               u.setEmail(rs.getString("email"));
               u.setRoles(rs.getString("roles"));
               u.setNomUtil(rs.getString("nom_util"));
               u.setPrenomUtil(rs.getString("prenom_util"));
               u.setTel(rs.getInt("tel"));
                u.setAdresse(rs.getString("adresse"));
               
               list.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public User getOneById(int id) {
        User u = new User();
        
        try{
             String req4 = "Select * from user where id = ? ";
             PreparedStatement pr4 = cnx.prepareStatement(req4);
             pr4.setInt(1, id);
             ResultSet rs = pr4.executeQuery();
             if (rs.next()){
                      u.setEmail(rs.getString("email"));
               u.setRoles(rs.getString("roles"));
               u.setNomUtil(rs.getString("nom_util"));
               u.setPrenomUtil(rs.getString("prenom_util"));
               u.setTel(rs.getInt("tel"));
                u.setAdresse(rs.getString("adresse"));
             }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
       return u;
    }
    
    public boolean verifierEmailBd(String email) { 
  
         
                try {
                    String req ="SELECT * FROM user  where email = ?";
                    PreparedStatement pr = cnx.prepareStatement(req);
                    pr.setString(1, email);
                    ResultSet rs = pr.executeQuery();
                    if(!rs.next()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                       alert.setTitle("Verifier adresse");
                       alert.setHeaderText("Veuillez saisir une adresse mail valide");
                       alert.showAndWait();
                   }
                }catch (SQLException ex ){
                    System.out.println(ex.getMessage());
                }
                return false;
    }
    
}
