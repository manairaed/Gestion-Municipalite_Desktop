/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import edu.esprit.entities.outils;
import edu.esprit.services.outilsCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class OutilsfrontController implements Initializable {

    @FXML
    private TableView<outils> tableoutils;
    @FXML
    private TableColumn<outils, String> nomOutils;
    @FXML
    private TableColumn<outils, String> quantite;
    @FXML
    private TableColumn<outils, String> image;
    @FXML
    private Button Newreservation;

    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) { 
        showOutils();
        
        // TODO
    }   
    
    public void showOutils()
    {   outilsCRUD  ls= new outilsCRUD();
     ObservableList<outils> list=ls.getAll(); 
 
    
  nomOutils.setCellValueFactory(new PropertyValueFactory<outils,String>("label_outils"));
   quantite.setCellValueFactory(new PropertyValueFactory<outils,String>("quantite"));
   image.setCellValueFactory(new PropertyValueFactory<outils,String>("image"));

   
   tableoutils.setItems(list);
     
        
    }
    
    
     @FXML
     private void Newreservation (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservationfront.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
