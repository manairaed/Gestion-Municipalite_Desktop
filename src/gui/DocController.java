/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Document;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.DocumentCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class DocController implements Initializable {

    @FXML
    private AnchorPane BTNAJOUTER;
    @FXML
    private Button document;
    @FXML
    private Button rendezvous;
    @FXML
    private TextField textname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void godocument(ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("ajouterDOC.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void gorendez(ActionEvent event) {
        try {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("afficherrendez.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void ajouter(MouseEvent event) {
        if (textname.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "name ou image invalide(s)", ButtonType.OK);
            a.showAndWait();
        } else {
            //int id = 0;
            String name = textname.getText();

            Document R = new Document(name);
            DocumentCRUD PC = new DocumentCRUD();

            PC.ajouterdocument(R);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterDOC.fxml"));

            try {
                Parent root = loader.load();
                DetailsrendezController dwc = loader.getController();
                dwc.settextdescription(R.getName());
  

                textname.getScene().setRoot(root);

            } catch (IOException ex) {
                System.out.println("error:" + ex.getMessage());
            }
        }
    }

}
