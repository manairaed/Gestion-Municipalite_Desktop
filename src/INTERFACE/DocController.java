/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.Document;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
public class DocController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane anchore;
    
    @FXML
    private GridPane cards;
    @FXML
    private Button ajouter;

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
                fxmlLoader.setLocation(getClass().getResource("userdocument.fxml"));
                HBox oneOffreCard = fxmlLoader.load();
               UserdocumentController contratCardController = fxmlLoader.getController();
                contratCardController.setContratData(contrats.get(i));

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                cards.add(oneOffreCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(oneOffreCard, new Insets(10, 10, 25, 10));
                oneOffreCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
    }

    
}
