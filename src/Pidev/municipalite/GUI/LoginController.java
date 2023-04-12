
package Pidev.municipalite.GUI;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    @FXML
    private Button btn_login;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

   ServiceUser us = new ServiceUser();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Login(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();
        User loggedInUser = authenticate (email,password);
        if(loggedInUser != null){
                        try{
                            Stage stage = (Stage) btn_login.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
                        
        }
    }
    
    public User authenticate (String email,String password){
        User u = us.Signin(email, password);
        return u;
    }
    
}
