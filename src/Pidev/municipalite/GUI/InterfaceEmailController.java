
package Pidev.municipalite.GUI;

import Pidev.municipalite.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceEmailController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private Button btn_verif;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Verifier(ActionEvent event) throws SQLException{
        String mail = emailTextField.getText();
       ServiceUser us = new ServiceUser();
       if (emailTextField.getText().isEmpty()|| !emailTextField.getText().contains("@")|| !emailTextField.getText().contains(".")){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Email invalide !");
            alert.showAndWait();
       }
       else if (!us.verifierEmailBd(mail)) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Verifier adresse");
            alert.setHeaderText("Veuillez saisir une adresse mail valide");
    
        }
    }
    
    @FXML
    private void goToLogin(MouseEvent event) {
          try{
                            Stage stage = (Stage) btn_verif.getScene().getWindow();
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
