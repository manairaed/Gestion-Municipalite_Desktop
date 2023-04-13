/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.DocumentCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AjouterDOCController implements Initializable {

    @FXML
    private TableView<Document> doctab;
    @FXML
    private TableColumn<Document, String> colid;
    @FXML
    private TableColumn<Document, String> colname;
    @FXML
    private TableColumn<Document, String> colimage;
    @FXML
    private Button btnsup;
    @FXML
    private Button btnedit;
    @FXML
    private Button document;
    @FXML
    private Button rendezvous;
    
    @FXML
    private Button AJOUTER;
    @FXML
    private TextField textid;
    @FXML
    private TextField textname;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         setCellValue();

        try {
            loadDate();
        } catch (ParseException ex) {
            Logger.getLogger(AfficherrendezController.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setCellValue() {

        doctab.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                Document p1 = doctab.getItems().get(doctab.getSelectionModel().getSelectedIndex());
                colid.setText(String.valueOf(p1.getId()));

                //date_livraison.setValue(p1.getDateLivraison()); 
                textid.setText(String.valueOf(p1.getId()));
                textname.setText(String.valueOf(p1.getName()));
                
            }
        });
    }


    @FXML
    private void supp(ActionEvent event) throws ParseException {
         DocumentCRUD cs = new DocumentCRUD();

        RendezVous c = new RendezVous();

        int id = Integer.parseInt(textid.getText());

        c.setId(id);

        cs.supprimer(id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("rendez-vous is deleted successfully!");
        alert.show();
        loadDate();
    }

    @FXML
    private void edit(ActionEvent event) throws ParseException {
        DocumentCRUD cs = new DocumentCRUD();

        Document c = new Document();

        c.setId(Integer.parseInt(textid.getText()));
        c.setName(textname.getText());

        
        

        cs.modifier(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("rendez vous is updated successfully!");
        alert.show();
        loadDate();
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
    private void ajouter(ActionEvent event) {
         try {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("doc.fxml"));
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
