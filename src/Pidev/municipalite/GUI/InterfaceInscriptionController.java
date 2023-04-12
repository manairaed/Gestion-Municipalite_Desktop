
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.Citoyen;
import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class InterfaceInscriptionController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btn_signup;

    ServiceUser us = new ServiceUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
        String email=emailField.getText();
        String password=passwordField.getText();
        String nom=nomField.getText();
        String prenom=prenomField.getText();
        String  tel=telField.getText();
         String adresse=adresseField.getText();
         
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
         String nameRegex = "^[a-zA-Z]+$";
         String phoneRegex = "^(2|5|9)[0-9]{7}$";
         User u = new Citoyen();
         
         if (!email.matches(emailRegex)){
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Email invalide !");
            alert.showAndWait();
         }else  if (!(password.length()>6)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Mot de passe  doit etre supérieure à 6  !");
            alert.showAndWait();
         }else if(!nom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("nom invalide !");
            alert.showAndWait();
         }else if(!prenom.matches(nameRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("prenom invalide !");
            alert.showAndWait();
         }
         else if(!tel.matches(phoneRegex)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("numéro invalide !");
            alert.showAndWait();
         }else if (adresse.isEmpty()){
        Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("adresse invalide !");
            alert.showAndWait();
    }else{
              u.setEmail(email);
         u.setPassword(password);
         u.setNomUtil(nom);
         u.setPrenomUtil(prenom);
         u.setRoles("["+"ROLE_CITOYEN"+"]");
         u.setTel(Integer.parseInt(tel));
         u.setAdresse(adresse);
         
         us.ajouter(u);
           try{
                            Stage stage = (Stage) btn_signup.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
         }
    }

    @FXML
    private void goToLogin(MouseEvent event) {
          try{
                            Stage stage = (Stage) btn_signup.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
}
