
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class InterfaceProfilController implements Initializable {

    @FXML
    private TextField emailTextfield;
    @FXML
    private TextField prenomTextfield;
    @FXML
    private TextField nomTextfield;
    @FXML
    private TextField adresseTextfield;
    @FXML
    private TextField telTextfield;
    @FXML
    private Button btn_modifier;
    
    ServiceUser us = new ServiceUser();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherProfilUtilisateur();
    }   
    
    private void afficherProfilUtilisateur() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("session.txt"));
            int userId = Integer.parseInt(reader.readLine());
            reader.close();
            
            User u = us.getOneById(userId);
            
            emailTextfield.setText(u.getEmail());
            prenomTextfield.setText(u.getPrenomUtil());
            nomTextfield.setText(u.getNomUtil());
            adresseTextfield.setText(u.getAdresse());
            telTextfield.setText(Integer.toString(u.getTel()));
            
        } catch (IOException ex) {
            System.out.println("Erreur lors de la lecture du fichier de session: " + ex.getMessage());
        }
    }
    
}
