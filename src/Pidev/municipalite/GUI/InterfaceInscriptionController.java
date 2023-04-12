
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.Citoyen;
import Pidev.municipalite.entites.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


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
    }
        
                 
          }
    
}
