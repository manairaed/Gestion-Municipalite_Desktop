/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.outils;
import piidev.municipalite.services.outilsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class ListOutilsController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private GridPane offreListContainer;
    @FXML
    private Button AddButt;
    
    @FXML
     private TextField search;

    @FXML
    private Button AddButt1;
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        
//        outilsCRUD  ls= new outilsCRUD();
//
//
//         List<outils> list=ls.getAll(); 
//            
//   
//      
//
//        // product list ------------------------------------------------
//        int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < list.size() ; i++) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
//                HBox OneoutilCard = fxmlLoader.load();
//               OneOutilListController outilCardController = fxmlLoader.getController();
//               outilCardController.setOffreData(list.get(i));
//
//                if (column == 1) {
//                    column = 0;
//                    ++row;
//                }
//                offreListContainer.add(OneoutilCard, column++, row);
//                // GridPane.setMargin(oneProductCard, new Insets(10));
//                GridPane.setMargin(OneoutilCard, new javafx.geometry.Insets(0, 10, 25, 10));
//                OneoutilCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void AjouterOutil(ActionEvent event)throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Add.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
//     @FXML
// private void searchButton(ActionEvent event) throws SQLException{
//     
//      outilsCRUD  ls= new outilsCRUD();
//
//        String marque= String.valueOf(search.getText()); 
//        
//
//         List<outils> list=ls.chercheroutils(search.getText()); 
//
//    //   System.out.println("recherche" + ls.chercheroutils(marque));
//              int column = 0;
//        int row = 1;
//        try {
//            for (int i = 0; i < list.size() ; i++) {
//
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
//                HBox OneoutilCard = fxmlLoader.load();
//               OneOutilListController outilCardController = fxmlLoader.getController(); 
//               outilCardController.setOffreData(list.get(i));
//
//                if (column == 1) {
//                    column = 0;
//                    ++row;
//                }
//                
//                offreListContainer.add(OneoutilCard, column++, row);
//                // GridPane.setMargin(oneProductCard, new Insets(10));
//                GridPane.setMargin(OneoutilCard, new javafx.geometry.Insets(0, 10, 25, 10));
//                OneoutilCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    
    @FXML
    private void affichestat(ActionEvent event)throws IOException {
         Parent fxml = FXMLLoader.load(getClass().getResource("Stat.fxml"));
        content_area.getChildren().removeAll();
        content_area.getChildren().setAll(fxml);
    }
    
    
    


/**
 * FXML Controller class
 *
 * @author zaghd
 */
//public class ListeDevisController implements Initializable {

   

    //@FXML
    //private TextField searchField;
    //@FXML
    //private TextField searchQuery;
    // private ObservableList<Devis> searchedProduits = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    outilsCRUD  ls= new outilsCRUD();
   // private Label lblNoResults;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Instancier le service de produit

        List<outils> devis;

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updateDisplayedDevis(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ListOutilsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ListOutilsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        List<outils> list=ls.getAll();
        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size(); i++) {
                
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneOutilListController devisCardController = fxmlLoader.getController();
                devisCardController.setOffreData(list.get(i));
                
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(OneCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneCard, new Insets(0, 10, 25, 10));
                OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateDisplayedDevis(String searchTerm) throws SQLException, IOException {
        // Clear the existing cards
        offreListContainer.getChildren().clear();

        // Create a new VBox layout container
        VBox cardsContainer = new VBox(10);

        // Get all Devis objects from the database
        List<outils> devisList = ls.getAll();

        // Loop through each Devis object
        boolean foundResults = false;
        for (outils outils : devisList) {
            // If the Devis object matches the search term, add it to the filtered VBox
            if (outils.getLabel_outils().toLowerCase().contains(searchTerm.toLowerCase()) ||outils.getQuantite().toLowerCase().contains(searchTerm.toLowerCase())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneOutilList.fxml"));
                HBox OneCard = fxmlLoader.load();
                OneOutilListController devisCardController = fxmlLoader.getController();

                // Set the data for the card
                devisCardController.setOffreData(outils);

                // Add the card to the filtered VBox
                cardsContainer.getChildren().add(OneCard);
                foundResults = true;

                // Set the margin for the card
                VBox.setMargin(OneCard, new Insets(10, 0, 0, 0));
                OneCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
            }
        }

        // Add the cards container to the main container
        offreListContainer.getChildren().add(cardsContainer);

        // Set the visibility of the "No results found" label
        //lblNoResults.setVisible(!foundResults);
    }

  

    

}
   
        
    

