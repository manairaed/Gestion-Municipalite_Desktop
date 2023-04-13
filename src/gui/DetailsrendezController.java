/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class DetailsrendezController implements Initializable {

    @FXML
    private TextField textid;
    @FXML
    private TextField textdescription;
    @FXML
    private TextField textdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  
    
    public void settextdescription (String message){
        this.textdescription.setText(message);
        
    }
    public void settextdate (String message){
        this.textdate.setText(message);
        
    }
        public void settextid (String message){
        this.textid.setText(message);
        
    }
    
}
