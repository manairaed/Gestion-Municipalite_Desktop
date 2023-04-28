
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
                        
public class InterfaceShowUserController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telField;
    @FXML
    private TextField adresseField;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supprimer;
    
    ServiceUser us = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModiferUser(ActionEvent event) {
          String email=emailField.getText();
        String nom=nomField.getText();
        String prenom=prenomField.getText();
        String  tel=telField.getText();
         String adresse=adresseField.getText();
         
         User r;
         r= new User( email,  nom,  prenom,  Integer.parseInt(tel),  adresse);
         us.modifier(r);
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Valider :: Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Utilsateur modifié");
                alert.showAndWait();  
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        
      
         
         
    }
    
    public void setText(User r)
    {
    
    }
    
}
