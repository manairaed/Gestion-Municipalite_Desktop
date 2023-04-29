/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class Dash implements Initializable {

    @FXML
    private AnchorPane side_anchorpane;
    @FXML
    private Pane pane;
    @FXML
    private Button dashbutuon;
    @FXML
    private Button RENDEZVOUSBTN;
    @FXML
    private Button documentbtn;
    @FXML
    private Pane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void home(ActionEvent event) throws IOException {
        
        Parent fxml = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }

    @FXML
    private void rendezvous(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("usermeeting.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);

    }

    @FXML
    private void document(ActionEvent event) throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("doc.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
    
    
}



