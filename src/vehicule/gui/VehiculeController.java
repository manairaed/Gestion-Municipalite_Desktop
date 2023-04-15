/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicule.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class VehiculeController implements Initializable {

    @FXML
    private TextField nommarque;
    @FXML
    private ComboBox<?> combocategorie;
    @FXML
    private ComboBox<?> combodispo;
    @FXML
    private Button ajoutervehicule;
    @FXML
    private TableView<?> tblaffichagevehicule;
    @FXML
    private MenuItem miSellSelected;
    @FXML
    private TableColumn<?, ?> tblClmProductId;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<?, ?> affichemarque;
    @FXML
    private TableColumn<?, ?> affichcategorie;
    @FXML
    private TableColumn<?, ?> Affichdispo;
    @FXML
    private Button verscategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void ajoutervehicule(ActionEvent event) {
        
    }

    @FXML
    private void miSellSelectedOnAction(ActionEvent event) {
    }

    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event) {
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
    }


    @FXML
    private void updatevehicule(ActionEvent event) {
    }

    @FXML
    private void btnDeletevehicule(ActionEvent event) {
    }
    @FXML
    private void retour(ActionEvent event) {
         try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Categorie.fxml"));
        Parent view_2=loader.load();
        
        Stage stage;
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);    
}

    }}