/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AddrendezController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane AddOffreContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
          
            // Ajouter offreContainer à la première ligne de AddoffreContainer
            FXMLLoader fxmlLoader1 = new FXMLLoader();
            fxmlLoader1.setLocation(getClass().getResource("AddrendezCard.fxml"));
            VBox offreContainer1 = fxmlLoader1.load();
            AddOffreContainer.add(offreContainer1, 0, 1);
            GridPane.setMargin(offreContainer1, new javafx.geometry.Insets(0, 10, 25, 10));
            offreContainer1.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }   
    }    
    

