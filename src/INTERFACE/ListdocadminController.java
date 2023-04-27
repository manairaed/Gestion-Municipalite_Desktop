/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.Document;
import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.DocumentCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class ListdocadminController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane anchore;
    @FXML
    private Button ajouter;
    @FXML
    private GridPane cards;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          DocumentCRUD cs = new DocumentCRUD();

             List<Document> contrats= cs.afficherdocument();
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < contrats.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("admindoccard.fxml"));
                HBox oneOffreCard = fxmlLoader.load();
               AdmindoccardController contratCardController = fxmlLoader.getController();
                contratCardController.setContratData(contrats.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                cards.add(oneOffreCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(oneOffreCard, new Insets(0, 10, 25, 10));
                oneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) throws IOException {
                RendezVous.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("Adddoc.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
    }
    
}
