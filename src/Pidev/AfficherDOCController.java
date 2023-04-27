/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pidev;

import entities.Document;
import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import services.DocumentCRUD;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AfficherDOCController implements Initializable {

    @FXML
    private Button doc;
    @FXML
    private Button rendezvous;
    @FXML
    private TableView<Document> doctab;
    @FXML
    private TableColumn<Document, String> colid;
    @FXML
    private TableColumn<Document, String> colname;
    @FXML
    private TableColumn<Document, String> colimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadDate();
        } catch (ParseException ex) {
            Logger.getLogger(AfficherDOCController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void loadDate() throws ParseException {
        DocumentCRUD ls= new DocumentCRUD();
         ObservableList<Document> list=FXCollections.observableArrayList(ls.afficherdocument());


        

        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        doctab.setItems(list);

    }

    @FXML
    private void godoc(ActionEvent event) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherDOC.fxml"));
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
    private void gorendezvous(ActionEvent event) {
         try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADD.fxml"));
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
