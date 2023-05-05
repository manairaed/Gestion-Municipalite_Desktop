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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class OneOutilListController  {

    @FXML
    private ImageView img;
    @FXML
    private Text OffreName;
    @FXML
    private Text stockProduit;
    @FXML
    private Text validiteOffre;
    @FXML
    private Text stockProduit11;
    @FXML
    private HBox editOffre;
    @FXML
    private HBox deleteOffre;
    @FXML
    private Text stockProduit1;

    /**
     * Initializes the controller class.
     */
     public void setOffreData(outils outil) {
            // Instancier le service de produit
           outilsCRUD  ls= new outilsCRUD();
//
            Image image = new Image(
                    getClass().getResource("/assets/OffresUploads/" + outil.getImage()).toExternalForm());
            img.setImage(image);
    
            OffreName.setText(outil.getLabel_outils());
            // get category Name
           validiteOffre.setText(outil.getQuantite());
    
           // priceOffre.setText("" + offre.getPrix());

            // deleteProduit btn click
            deleteOffre.setId(String.valueOf(outil.getId()));

            deleteOffre.setOnMouseClicked(event -> {
            System.out.println("ID du offre à supprimer : " + outil.getId());
            try {
                ls.supprimer(outil.getId());
               
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // supprimer le contenu de la liste et afficher la nouvelle liste(apres
            // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOutils.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
        // END deleteProduit btn click

        // editProduit btn click
       editOffre.setId(String.valueOf(outil.getId()));

       editOffre.setOnMouseClicked(event -> {
           System.out.println("ID du produit à modifier : " + outil.getId());
       outil.setIdOutil(outil.getId());

           //    outil.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de AddProduct.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // END editProduit btn click


            
    
    
    
        }
    
        
    }  
        
       
    

