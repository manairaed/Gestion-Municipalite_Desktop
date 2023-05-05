/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package piidev.municipalite.GUI;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class DashController implements Initializable {

    @FXML
    private AnchorPane side_anchorpane;
    @FXML
    private Pane pane;
    @FXML
    private Button dashbutuon;
    
    @FXML
    private Button OutilBUTT;
    @FXML
    private Button RESERVATIONBUTT;
    @FXML
    private Pane content_area;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

 
            

    @FXML
    private void ReserveList(ActionEvent event)throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("ReserveList.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    private void home(ActionEvent event) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("dashbord.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    private void AfficheList(ActionEvent event) throws IOException {
       Parent fxml = FXMLLoader.load(getClass().getResource("ListOutils.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }

    @FXML
    private void list(ActionEvent event)throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("ListOutils.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
            

}
    

