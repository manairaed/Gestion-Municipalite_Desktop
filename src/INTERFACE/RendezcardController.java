/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class RendezcardController implements Initializable {

    @FXML
    private Text stockProduit1;
    @FXML
    private Text OffreName;
    @FXML
    private Text stockProduit;
    @FXML
    private Text validiteOffre;
    @FXML
    private Text stockProduit11;
    @FXML
    private HBox priceHbox;
    @FXML
    private Text stockProduit12;
    @FXML
    private ImageView edit;
    @FXML
    private ImageView supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void supp(MouseEvent event) {
    }
     public void setOffreData(RendezVous rendezvous) {
         
            // Instancier le service de produit
             RendezVousCRUD cs = new RendezVousCRUD();
             

            
    
            OffreName.setText(rendezvous.getDescription());
            // get category Name
            validiteOffre.setText(rendezvous.getDate_ren().toString());
    
            

            // deleteProduit btn click
             supp.setId(String.valueOf(rendezvous.getId()));

            supp.setOnMouseClicked(event -> {
            System.out.println("ID du offre à supprimer : " + rendezvous.getId());
            cs.supprimerrendez(rendezvous.getId()); // supprimer le contenu de la liste et afficher la nouvelle liste(apres
                 // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("usermeeting.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content");

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
        edit.setId(String.valueOf(rendezvous.getId()));

        edit.setOnMouseClicked(event -> {
            System.out.println("ID du produit à modifier : " + rendezvous.getId());
            rendezvous.setIdrendez(rendezvous.getId());

            rendezvous.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Addrendez.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content");

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
